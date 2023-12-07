char * mstrcat(char * dest, char* src){
    int dlen = 0;
    int slen = 0;
    while (*(dest + dlen) != '\0'){
        dlen++;
    }

    while (*(src + slen) != '\0'){
        slen++;
    }
    char * res = (char *) malloc((dlen+slen+1)*sizeof(char));
    int i = 0;
    int j = 0;
    while(*(dest + i) != '\0'){
        *(res+i) = *(dest+i);
        i++;
    }
    while(*(src + j) != '\0'){
        *(res+i) = *(src+j);
        j++;
        i++;
    }
    *(res+i) = '\0';
    free(dest);
    free(src);
    return res;
}