package com.sym.demo;

/**
 * @author yongming.su
 * @version 1.0
 * @date 2020/6/18 10:00
 */
public class ReturnDemo {

    /**
     * try有return,finally也有return,以finally的为准
     * @return
     */
    public static int noException(){
        int i=10;
        try{
            System.out.println("i in try block is："+i); //10
            i--; //9
            return --i; //8
        }
        catch(Exception e){
            --i;
            System.out.println("i in catch - form try block is："+i);
            return --i;
        }
        finally{
            System.out.println("i in finally - from try or catch block is："+i); //8
            return ++i; //9 返回这个
        }
    }

    public static int tryCatchReturn(){
        int i=10;
        try{
            System.out.println("i in try block is："+i); //10
            return --i; //9
        }
        catch(Exception e){
            --i;
            System.out.println("i in catch - form try block is："+i);
            return --i;
        }
        finally{
            System.out.println("i in finally - from try or catch block is："+i); //9
            --i; //8
            System.out.println("i in finally block is："+i); //8
        }
    }

    /**
     * finally的return的优先级最高
     * 但catch的return也会执行
     * @return
     */
    public static int tryCatchFinallyReturn(){
        int i=10;
        try{
            System.out.println("i in try block is："+i); //10
            i = i/0;
            return --i;
        }
        catch(Exception e){
            System.out.println("i in catch - form try block is："+i); //10
            --i; //9
            System.out.println("i in catch block is："+i); //9
            return --i; //8
        }
        finally{
            System.out.println("i in finally - from try or catch block is--"+i); //8
            --i; //7
            System.out.println("i in finally block is--"+i); //7
            return --i; //6
        }
    }

    public static int tryExCatchReturn(){
        int i=10;
        try{
            System.out.println("i in try block is："+i); //10
            i=i/0;
            return --i;
        }catch(Exception e){
            System.out.println("i in catch - form try block is："+i); //10
            return --i; //9 返回这个
        }finally{

            System.out.println("i in finally - from try or catch block is："+i); //9
            --i; //8
            System.out.println("i in finally block is："+i); //8
        }
    }

    public static int tryExCatchExReturn() {
        int i = 10;
        try {
            System.out.println("i in try block is：" + i); //10
            i = i / 0;
            return --i;
        } catch (Exception e) {
            System.out.println("i in catch - form try block is：" + i); //10
            int j = i / 0;
            return --i;
        } finally {

            System.out.println("i in finally - from try or catch block is：" + i); //10
            --i; //9
            --i; //8
            System.out.println("i in finally block is：" + i); //8
            return --i; //7 返回这个
        }
    }


    public static void main(String[] args){
        System.out.println("###" + noException()); //9
        System.out.println("###" + tryCatchReturn()); //9
        System.out.println("###" + tryCatchFinallyReturn()); //6
        System.out.println("###" + tryExCatchReturn()); //9
        System.out.println("###" + tryExCatchExReturn()); //7
    }

}
