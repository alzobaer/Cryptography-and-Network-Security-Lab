#include <bits/stdc++.h>
using namespace std;

string doEncoding(string msg , int width){
    int msgLen = msg.size();
    int row = msgLen / width , rem = msgLen % width , x = 0 , y = 0;
    string ans(msgLen,'\0');

    for(int i = 0 ; i < msgLen ; i++){
        if(i % width == 0)
            x = y , y++;
        else if(i % width <= rem)
            x++;

        ans[x] = msg[i];
        x += row;
    }
    cout<<ans<<' '<<endl;
    return ans;
}

string doDecoding(string msg , int width){
    int msgLen = msg.size();
    int row = msgLen / width , rem = msgLen % width , x = 0 , y = 0;
    string ans(msgLen,'\0');

    for(int i = 0 ; i < msgLen ; i++){
        if(i % width == 0)
            x = y , y++;
        else if(i % width <= rem)
            x++;

        ans[i] = msg[x];
        x += row;
    }
    return ans;
}
int main(){
    string msg;
    msg = "DEPARTMENT OF COMPUTER SCIENCE AND TECHNOLOGY UNIVERSITY OF RAJSHAHI BANGLADESH";
    int width = 5;

    string enc = doEncoding(msg,width);
    cout<<doDecoding(enc,width)<<endl;
    return 0;
}