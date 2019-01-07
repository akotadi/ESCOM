#include  <stdio.h>
#include  <sys/types.h>

#define   MAX_COUNT  10

void  ChildProcess(void);                /* child process prototype  */
void  ParentProcess(void);               /* parent process prototype */

void  main(void)
{
     pid_t  pid;

     pid = fork();
     printf("%d\n",pid);
     if (pid == 0) {
          pid = fork();
          printf("%d\n",pid);
          if (pid == 0)
          {
               ChildProcess();
          }
          else{
               ParentProcess();
          }
     }
     else {
          pid = fork();
          printf("%d\n",pid);
          if (pid == 0)
          {
               ChildProcess();
          }
          else{
               ParentProcess();
          }
     }
}

void  ChildProcess(void)
{
     int   i;

     for (i = 1; i <= MAX_COUNT; i++)
          printf("   This line is from child, value = %d\n", i);
     printf("   *** Child process is done ***\n");
}

void  ParentProcess(void)
{
     int   i;

     for (i = 1; i <= MAX_COUNT; i++)
          printf("This line is from parent, value = %d\n", i);
     printf("*** Parent is done ***\n");
}