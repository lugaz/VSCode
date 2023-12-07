#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void filedump(char * p , int len) {
    
    FILE* fp = fopen(p, "r");
    char* file = (char*) malloc(len + 1 * sizeof(char));
    file[len] = '\0';
    fread(file, len, 1, fp);
    int n = 0;
    int c;
    unsigned long start = file;
     
    while (n < len) {
        fprintf(stdout, "0x%016lX: ", (unsigned long) (file + n - file));
            for (int i = n; i < len && i < n + 16; i++) {
                c = file[i]&0xFF; 
                fprintf(stdout, "%02X ", c);
            }
            for (int i = len; i < n + 16; i++) {
                fprintf(stdout, "   ");
            }
            fprintf(stdout, " ");
            for (int i = n; i < len && i < n + 16; i++) {
                c = file[i]&0xFF;
                fprintf(stdout, "%c", ((c >= 32 )&& (c < 127))?c:'.');
            }
            n += 16;
            fprintf(stdout, "\n");
    }
}
int
main(int argc, char **argv) {
    if (argc < 2) {
        printf("Usage: myfiledump file [maxbytes]\n");
        return (-1);
    }
    char* fileName = argv[1];
    FILE* fp = fopen(fileName, "r");
    if (fp == NULL) {
        printf("Error opening file \"%s\"\n", fileName);
        return (-1);
    }
    fseek(fp, 0L, SEEK_END);
    int len = ftell(fp);
    fseek(fp, 0L, SEEK_SET);
    if (argc > 2) {
        int arglen = atoi(argv[2]);
        if (arglen < len) {
            len = arglen;
        }
    }
    filedump(argv[1], len);
}


