## Core Java Examples

## View/Read compiled code

To view what your Java code compiles into for the JVM, you need to understand the bytecode that the Java compiler (javac) generates when it compiles your .java file into a .class file. The .class file contains bytecode, which the Java Virtual Machine (JVM) interprets.

You can disassemble the .class file to inspect the bytecode using the javap tool, which is part of the Java Development Kit (JDK).

1. Compile .java to
```bash
javac HelloWorld.java
```

2. Disassemble the .class file with javap
```bash
javap -c HelloWorld
```

```
javap -verbose HelloWorld: Provides detailed information about the class, including constant pool entries, field types, method signatures, and more.
javap -private HelloWorld: Shows all fields and methods, including private ones.
javap -p HelloWorld: This is similar to -private, showing all members, including private fields and methods.
```
