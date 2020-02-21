/**
 * @version 1.10 1999-11-13
 * @author Cay Horstmann
 */

#include "Employee.h"

#include <stdio.h>

JNIEXPORT void JNICALL Java_Employee_raiseSalary
  (JNIEnv* env, jobject obj_this, jdouble byPercent)
{  /* get the class */
   jclass class_Employee = (*env)->GetObjectClass(env,
      obj_this);

   /* get the field ID */
   jfieldID id_salary = (*env)->GetFieldID(env,
      class_Employee, "salary", "D");

   /* get the field value */
   jdouble salary = (*env)->GetDoubleField(env, obj_this,
      id_salary);

   salary *= 1 + byPercent / 100;

   /* set the field value */
   (*env)->SetDoubleField(env, obj_this, id_salary, salary);
}

