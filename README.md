Finagle
-------
>Finagle http and thrift client/server tests.

JDK
---
>JDK 8 is required. See .sbtopts

Thrift
------
1. See /src/main/thrift/echo.service.thrift for IDL.
2. See plugins.sbt for comment and scrooge plugin.
3. sbt clean compile generates 3 source files in target/scala-2.13/src_managed/main/thrift/objektwerks
4. Don't move the generated sources. Simply reference them in your code. See EchoServer and EchoClient.

Test
----
1. sbt clean test

Run
---
1. sbt clean run
>Multiple main classes detected. Select one to run:
* [1] objektwerks.EchoServer
* [2] objektwerks.NowServer
>Enter number:

1. EchoServer: Contains an embedded EchoClient. See console output!
2. NowServer: curl -D - 127.0.0.1:7979
>Press Ctrl-C to stop a server.