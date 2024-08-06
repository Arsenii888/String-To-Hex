#include <jni.h>
#include <string>
#include <android/log.h>
extern "C"
JNIEXPORT jstring JNICALL
Java_com_u063_nativeboot_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
std::string getHex(int64_t data){
    int64_t dec=data;
    int64_t usage=0;
    int64_t currect=0;
    int64_t hex[16];
    for(int i=0; i<16; i++){
        hex[i]=0;
    }
    int64_t old=dec;
    while(dec!=0){
        old=dec;
        usage=dec/16;
        dec/=16;
        hex[currect]=old-(usage*16);
        __android_log_print(ANDROID_LOG_ERROR, "TRACKERS", "%s", std::to_string(old-(usage*16)).c_str());
        currect+=1;
        dec=usage;
            /*if(dec-16<0){
                hex[currect]=usage;
                currect+=1;
                break;
            }*/


        /*if(dec<16){
            hex[currect]=dec;
            break;
        }*/
    }
    std::string result="";
    for(int i=currect-1; i>-1; i--){
        if(hex[i]==10){
            result+="A";
        }else if(hex[i]==11){
            result+="B";
        }else if(hex[i]==12){
            result+="C";
        }else if(hex[i]==13){
            result+="D";
        }else if(hex[i]==14){
            result+="E";
        }else if(hex[i]==15){
            result+="F";
        }else if(hex[i]<10){
            result+=std::to_string(hex[i]);
        }
        __android_log_print(ANDROID_LOG_ERROR, "TRACKERS", "%s", result.c_str());
    }
    return result;
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_u063_nativeboot_MainActivity_getInt(JNIEnv* env,jobject,jchar str){
    int64_t data=str;
   // __android_log_print(ANDROID_LOG_ERROR, "TRACKERS", "%s", std::to_string(data).c_str());
    //int64_t* ptr=getHex(data);
    std::string hello = "Hello from C++";
    return env->NewStringUTF(getHex(data).c_str());
}
