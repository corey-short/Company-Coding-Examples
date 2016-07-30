


boolean HasWordsInOrder(vector<String> sentence, vector<String> words, int start_index, int* end_index) {
    int words_index = 0;
    for (String sentence_word : sentence) {
        if (sentence_word.equals(words[words_index]) {
            words_index++;
        }
        if (words_index == words.size()) {
            return true;
        }
    }
    return false;
// 1. ask
// 2. clarify solution
// 3. write the code
// 4. double check answer


// find find find them all
// [find find find]



// start with function prototype
// Go through example of solution to dbl check it works
void FindShortestSubSequence(vector<String> sentence, vector<string> words) {
    // sanity check on input
    //    vector<String> sentence_words = sentence.BreakUp();
    int best_start = -1;
    int best_end = -1;
    for (int i = 0; i < sentence.size(); i++) {
        String cur_sentence_word = sentence[i];
        if (cur_sentence_word.equals(words[0]) {
            int end_index = -1;
            if (HasWordsInOrder(sentence, words, index, &end_index)) {
                if (best_start == -1 || end_index - i < best_end - best_start) {
                    best_start = i;
                    best_end = end_index;
                }
            }
        }
    }
    if (best_start == -1) {
        return "";
    } else {
        return sentence.getSubstring(best_start, best_end);
    }
}
