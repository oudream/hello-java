#!/usr/bin/perl

# Version 1.02 1999-08-27
# Author Cay Horstmann

($url) = @ARGV;

$url =~ tr/+/ /;
$url =~ s/%([a-fA-F0-9][a-fA-F0-9])/pack("C", hex($1))/eg; 

$pos = index($url, "URL=http://");

if ($pos != 0) 
{  die "Sorry--can only recognize URL=http://server/file";
}

$port = 80;

$pos = 11;
$pos2 = index($url, "/", $pos);
if ($pos2 < 0) 
{  die "Sorry--can only recognize //server/file";
}

$server_name = substr($url, $pos, $pos2 - $pos);
$file_name = substr($url, $pos2);
$AF_INET = 2;
$SOCK_STREAM =1;

$sockaddr = 'S n a4 x8';

($name, $aliases, $proto) = getprotobyname ('tcp');
($name,$aliases,$type,$len,$thataddr) 
   = gethostbyname($server_name);
$that = pack($sockaddr, $AF_INET, $port, $thataddr);

if (!socket (S, $AF_INET, $SOCK_STREAM, $proto))
{  die $!;
}

if (!connect (S, $that)) 
{  die $!;
}

select(S); $|=1; select(STDOUT);

$command = "GET ".$file_name;

print S $command."\r\n";

print "Content-type: text/html\n\n";
while (<S>)
{  print;
}
