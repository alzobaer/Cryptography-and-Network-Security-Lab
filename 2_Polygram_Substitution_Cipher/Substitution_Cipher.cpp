#include <bits/stdc++.h>
using namespace std;

map < string , string > blocks;

void create_dictionary(int block_sz){
    ifstream input("./sub.txt",ios::in);
    char ch;
    string key ,val, text;
    bool flag = true;

    while(getline(input,text)){
        key = text.substr(0,block_sz);
        val = text.substr(block_sz+1,block_sz);
        blocks[key] = val;
    }

    input.close();
}

string encode(string text, int block_sz){
    int i, j;
    string block , cipher_text;
    cout<<text<<endl;
    for(i = 0 ; i < text.size() ; i += block_sz){
        block = text.substr(i,block_sz);
        cipher_text += (!blocks[block].empty())? blocks[block] : block;
    }

    return cipher_text;
}

string decode(string cipher_text , int block_sz){
    string text, block;
    int i , j;

    for(i = 0 ; i < cipher_text.size() ; i+= block_sz){
        block = "";
        for(j = 0 ; j < block_sz && i + j < cipher_text.size() ; j++)
            block += cipher_text[i+j];
    
        auto itr = blocks.find(block);
        text += (itr != blocks.end())? itr->first : block;
    }
    return text;
}

int main(){
    int block_sz = 3;
    string text, encryptText, decryptText , tmp;
    char ch;
    ifstream in("./input.txt",ios::in);

    create_dictionary(block_sz);

    while(getline(in,tmp)){
        text += tmp;
    }

    encryptText = encode(text,block_sz);
    decryptText = decode(text,block_sz);

    cout<<"Input Text: "<<text<<endl;
    cout<<"Block_Size: "<<block_sz<<endl;
    cout<<"Encrypted Message: "<<encryptText<<endl;
    cout<<"After Decryption, Message: "<<decryptText<<endl;

    in.close();

    return 0;
}