int countlord(char *filename, char *word, char *line, int size) {
    FILE* fd;
    fd = fopen(filename, "r");
    int sum = 0;
    if (fd == NULL) {
        return -1;
    } else {
        int word_len = strlen(word);
        int line_len = 0;
        char *line_start = line;
        char c;
        while ((c = fgetc(fd)) != EOF) {
            if (c == '\n') {
               
                line[line_len] = '\0'; 
                char *pos = line_start;
                while ((pos = strstr(pos, word)) != NULL) {
                    if ((pos == line_start || !isalpha(*(pos - 1))) && 
                        (!isalpha(*(pos + word_len)) || *(pos + word_len) == '\0')) {
                        
                        sum++;
                    }
                    pos += word_len; 
                }
                line_len = 0;
                line_start = line;
            } else {
                if (line_len < size - 1) {
                    line[line_len] = c;
                    line_len++;
                }
            }
        }
        
        if (line_len > 0) {
            line[line_len] = '\0';
            char *pos = line_start;
            while ((pos = strstr(pos, word)) != NULL) {
                if ((pos == line_start || !isalpha(*(pos - 1))) && 
                    (!isalpha(*(pos + word_len)) || *(pos + word_len) == '\0')) {
                
                    sum++;
                }
                pos += word_len; 
            }
        }
        fclose(fd);
        return sum;
    }
}
