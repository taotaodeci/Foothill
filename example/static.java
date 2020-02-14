public class HelloWorld{
     public static void main(String []args){
        System.out.println("Hello World");
        MyClass a = new MyClass();
        MyClass b = new MyClass();
        System.out.println(MyClass.value);
        System.out.println(a.value);
        System.out.println(b.value);
        a.value = 1;
        System.out.println(MyClass.value);
        System.out.println(a.value);
        System.out.println(b.value);
/* Output is
Hello World
0
0
0
1
1
1
*/      
     }
}

class MyClass {
   static int value = 0;
}
