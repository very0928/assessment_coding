package ds_algorithm.classic_algorithms;

import java.util.Scanner;
import java.util.Stack;

public class Dec_19_2024_2_Calculator {
    public static void main(String[] args){
        String ss = change_bracket("(1+(4+5+2)-3)+(6+8)");
        System.out.println("转换括号之后："+ss);
        String sss = mid2post(ss);
        System.out.println("转换成后缀表达式后："+sss);
        System.out.println("计算结果："+calculatePost(sss));
        //现在 不可以考虑负数 不可以考虑 （10以外的数字）多位数字
    }
    public static String change_bracket(String s) {
        char[] c = s.toCharArray();
        for(int i=0; i<s.length(); i++){
            if(c[i]=='{' || c[i]=='['){
                c[i] = '(';
            }
            if(c[i]=='}'|| c[i]==']'){
                c[i] = ')';
            }
        }
        return String.valueOf(c);
    }

    //中缀表达式-->后缀表达式
    public static String mid2post(String s){
        StringBuffer sb = new StringBuffer();

        Stack<Character> st = new Stack<>();
        String postString = "";
        for(int i=0; i<s.length(); i++){
            char cur = s.charAt(i);
            //考虑数字不止一位
            if(cur>='0' && cur<='9'){
                sb.append(cur);
            }else if(cur=='+'||cur=='-'||cur=='*'||cur=='/'){

                if(st.isEmpty()){
                    st.push(cur);
                }else{
                    while(!st.isEmpty() && opPriority(st.peek())>=opPriority(cur) ){
                        sb.append(st.pop());
                    }
                    st.push(cur);
                }
            }else if(cur == '('){
                st.push(cur);
            }else if(cur==')'){
                while(st.peek() != '('){
                    sb.append(st.pop());
                }
                st.pop();
            }
        }
        while(!st.isEmpty()){
            sb.append(st.pop());
        }
        return sb.toString();
    }

    public static int opPriority(char c){
        if(c=='(') return 1;
        else if(c=='+'||c=='-') return 2;
        else if(c=='*'||c=='/') return 3;
        else if(c==')') return 4;
        return -1;
    }

    public static int calculatePost(String s){
        Stack<Integer> st = new Stack<>();
        for(int i=0; i<s.length();i++){
            char cur = s.charAt(i);
            if(cur>='0' && cur<='9'){
                st.push(Integer.parseInt(String.valueOf(cur)));
            }else{
                int op2 = st.pop();
                int op1 = st.pop();
                System.out.println("操作数："+op1+" "+op2+" "+cur);
                int cul = calOps(op1,op2,cur);
                st.push(cul);
            }
        }
        return st.pop();
    }

    public static int calOps(int op1, int op2, char symble){
        if(symble=='+'){
            return op1+op2;
        }else if(symble=='-'){
            return op1-op2;
        }else if(symble=='*'){
            return op1*op2;
        }else if(symble=='/'){
            return op1/op2;
        }
        return -1;
    }
}
