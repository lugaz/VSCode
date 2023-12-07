int strcspn(char * str, char * reject){
    int res = 0;
    int i,j;
    for (i = 0; *(str + i) != '\0'; i++){
        for(j = 0; *(reject + j) != '\0'; j++){
            if(*(str + i) == *(reject + j)){
                return res;
            }
        }
        res++;
    } 
    return res;
}