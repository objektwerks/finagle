Finagle
-------
>Finagle feature tests.

JDK
---
>JDK 8 is required. See .sbtopts

Thrift
------
1. See /src/main/thrift/echo.service.thrift for IDL.
2. See plugins.sbt for comment and scrooge plugin.
3. sbt clean compile generates 3 source files in target/scala-2.13/src_managed/main/thrift/objektwerks
>It's not clear, at this point, the best practice(s) for using the generated source.

Test
----
1. sbt clean test

Run
---
1. sbt clean run
2. curl -D - 127.0.0.1:7777