/**
 * version 1.01 1999-08-27
 * author Cay Horstmann
 */

#include <netdb.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAXLINE 512
#define MAXNAME 128
#define HTTP 80

unsigned writen(fd, vptr, n)
int fd;
char* vptr;
unsigned n;
{  unsigned nleft;
   unsigned nwritten;
   char* ptr;

   ptr = (char*)vptr;
   nleft = n;
   while (nleft > 0)
   {  if ((nwritten = write(fd, ptr, nleft)) <= 0)
         return nwritten;
      nleft -= nwritten;
      ptr += nwritten;
   }
   return n - nleft;
}

unsigned readline(fd, vptr, maxlen)
int fd;
char* vptr;
int maxlen;
{  unsigned n;
   unsigned rc;
   char* ptr;
   char c;

   ptr = vptr;
   for (n = 1; n < maxlen; n++)
   {  if ((rc = read(fd, &c, 1)) == 1)
      {  *ptr++ = c;
         if (c == '\n')
         {  *ptr = 0;
            return n;
         }
      }
      else if (rc == 0)
      {  if (n == 1) return 0;
         else
         {  *ptr = 0;
            return n;
         }
      }
      else
         return -1;
   }
   *ptr = 0;
   return n;
}

void error(msg)
char* msg;
{  fputs(msg, stderr);
   fputc('\n', stderr);
   exit(1);
}

void url_decode(in, out, outlen)
char* in;
char* out;
int outlen;
{  int i = 0;
   int j = 0;
   while (in[i] != '\0' && j < outlen - 1)
   {  if (in[i] == '+') out[j] = ' ';
      else if (in[i] == '%')
      {  int ch;
         sscanf(in + i + 1, "%x", &ch);
         out[j] = ch;
         i += 2;
      }
      else out[j] = in[i];
      i++;
      j++;
   }
   out[j] = 0;
}

int main(argc, argv)
int argc;
char** argv;
{  int sockfd;
   struct sockaddr_in serv_addr;
   int n;
   char* name;
   struct hostent* hostptr;
   char url[MAXLINE + 1];
   char sendline[MAXLINE + 1];
   char recvline[MAXLINE + 1];
   char server_name[MAXNAME];
   char file_name[MAXLINE];
   int port;
   int service = 0;
   char* p;
   char* q;

   url_decode(argv[1], url, sizeof(url));

   p = strstr(url, "URL=http://");
   if (p != url)
      error("Sorry--can only recognize URL=service://server/file");
   service = HTTP;
   p += strlen("URL=http://");
   q = strchr(p, '/');
   if (q == NULL)
      error("Sorry--can only recognize //server/file");
   strncpy(server_name, p, q - p);
   server_name[q - p] = '\0';
   strncpy(file_name, q, sizeof(file_name) - 1);
   file_name[sizeof(file_name) - 1] = '\0';
   port = service;

   if ((sockfd = socket(PF_INET, SOCK_STREAM, 0)) < 0)
      error("Can't open stream socket");

   bzero((char*)&serv_addr, sizeof(serv_addr));
   serv_addr.sin_family = AF_INET;
   hostptr = gethostbyname(server_name);
   if (hostptr == 0) error("Can't find host");
   name = inet_ntoa(*(struct in_addr*)*hostptr->h_addr_list);
   serv_addr.sin_addr.s_addr = inet_addr(name);
   serv_addr.sin_port = htons(port);

   if (connect(sockfd, (struct sockaddr*)&serv_addr, sizeof(serv_addr)) < 0)
      error("Can't connect to server");

   sendline[sizeof(sendline) - 1] = 0;
   if (service == HTTP)
   {  strcpy(sendline, "GET ");
      strncat(sendline, file_name, sizeof(sendline) - 1
         - strlen(sendline));
   }
   strncat(sendline, "\r\n", sizeof(sendline) - 1
      - strlen(sendline));

   n = strlen(sendline);
   if (writen(sockfd, sendline, n) != n)
      error("Write error on socket");

   fputs("Content-type: text/html\n\n", stdout);

   do
   {  n = readline(sockfd, recvline, MAXLINE);
      if (n < 0)
         error("Read error on socket");
      else if (n > 0)
      {  recvline[n] = 0;
         fputs(recvline, stdout);
      }
   } while (n > 0);

   return 0;
}
