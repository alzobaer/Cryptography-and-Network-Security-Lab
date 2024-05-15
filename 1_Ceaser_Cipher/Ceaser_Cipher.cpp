#include <iostream>

using namespace std;

string encryption(string text , int shift){

    string result;

    for(auto &i : text){

        if(i >= 'A' && i <= 'Z')
            result += (i - 'A' + shift) % 26 + 'A';
        
        else if(i >= 'a' && i <= 'z')
            result += (i - 'a' + shift) % 26 + 'a';
        
        else
            result += i;
    }

    return result;
}

string decryption(string encText , int shift){
    
    string result;

    for(auto &i : encText){

        if(i >= 'A' && i <= 'Z')
            result += (i - 'A' - shift + 26) % 26 + 'A';
        
        else if(i >= 'a' && i <= 'z')
            result += (i - 'a' - shift + 26) % 26 + 'a';
        
        else
            result += i;
    }

    return result;
}

int main(){
    int shift = 5;
    string text , encryptText , decryptText;

    text = "ABCZ1590abcz";

    encryptText = encryption(text , shift);
    decryptText = decryption(encryptText , shift);

    cout<<"Input Text: "<<text<<endl;
    cout<<"Shift: "<<shift<<endl;
    cout<<"Encrypted Message: "<<encryptText<<endl;
    cout<<"After Decryption, Message: "<<decryptText<<endl;

    return 0;
}