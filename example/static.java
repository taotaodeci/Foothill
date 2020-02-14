public class HelloWorld{
     public static void main(String []args){
        System.out.println("Hello World");
        MyClass a = new MyClass();
        MyClass b = new MyClass();
        System.out.println(MyClass.value);
        System.out.println(a.value);
        System.out.println(b.value);
        a.value = 1;
        System.out.println(MyClass.value);  // also changed
        System.out.println(a.value);
        System.out.println(b.value);  // also changed
/* Output is
Hello World
0
0
0
1
1
1
*/      
        // System.out.println(MyClass.nonStaticValue);  WRONG, Can't access non-static variable
        System.out.println(a.nonStaticValue);  // 1
        System.out.println(b.nonStaticValue);  // 1
        a.nonStaticValue = 2;
        System.out.println(a.nonStaticValue);  // 2
        System.out.println(b.nonStaticValue);  // 1 not changed
     }
}

class MyClass {
   static int value = 0;
   int nonStaticValue = 1;
     
     static boolean valueIsZero() {
          // return nonStaticValue;   WRONG, CAN't access non-static variable.
          return value == 0;
     }
     
     boolean nonStaticValueIsZero() {
          return nonStaticValue == 0;
     }
     
     boolean valueEqual() {
          return value == nonStaticValue;  // OK, can access static variable
     }
}
