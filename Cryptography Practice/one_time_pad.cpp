#include <bits/stdc++.h>
using namespace std;

string doEncode(string msg , string pad){
    string ans;
    for(int i = 0 ; i < msg.size() ; i++){
        if(islower(msg[i]))
            ans += ((msg[i]-'a' + pad[i] - 'a') % 26) + 'a';
        else
            ans += msg[i];
    }
    return ans;
}

string doDecode(string enc , string pad){
    string ans;
    for(int i = 0 ; i < enc.size() ; i++){
        if(islower(enc[i]))
            ans += (((enc[i]-'a') - (pad[i] - 'a') + 26) % 26) + 'a';
        else 
            ans += enc[i];
    }
    return ans;
}
int main(){
    string msg , pad , enc;

    getline(cin,msg);
    getline(cin,pad);

    enc = doEncode(msg,pad);
    cout<<doDecode(enc,pad)<<endl;

    return 0;
}