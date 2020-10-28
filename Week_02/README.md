**Week02 作业题目（周四）：**

**1.**使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例。
```
#串行
cutiedeMacBook-Pro:第二周 cutie$ javac -encoding UTF-8 GCLogAnalysis.java 
cutiedeMacBook-Pro:第二周 cutie$ java -XX:+UseSerialGC -Xms2g -Xmx2g -XX:+PrintGCDetails GCLogAnalysis
正在执行...
[GC (Allocation Failure) [DefNew: 559232K->69888K(629120K), 0.0672075 secs] 559232K->152235K(2027264K), 0.0672337 secs] [Times: user=0.04 sys=0.03, real=0.07 secs] 
[GC (Allocation Failure) [DefNew: 629120K->69888K(629120K), 0.0888762 secs] 711467K->281580K(2027264K), 0.0889003 secs] [Times: user=0.06 sys=0.04, real=0.09 secs] 
[GC (Allocation Failure) [DefNew: 629120K->69887K(629120K), 0.0630784 secs] 840812K->396710K(2027264K), 0.0631046 secs] [Times: user=0.04 sys=0.02, real=0.06 secs] 
[GC (Allocation Failure) [DefNew: 629119K->69887K(629120K), 0.0727922 secs] 955942K->531966K(2027264K), 0.0728164 secs] [Times: user=0.05 sys=0.02, real=0.08 secs] 
[GC (Allocation Failure) [DefNew: 629119K->69886K(629120K), 0.0645490 secs] 1091198K->652045K(2027264K), 0.0645733 secs] [Times: user=0.04 sys=0.02, real=0.07 secs] 
[GC (Allocation Failure) [DefNew: 629118K->69887K(629120K), 0.0668978 secs] 1211277K->777974K(2027264K), 0.0669217 secs] [Times: user=0.05 sys=0.03, real=0.06 secs] 
执行结束!共生成对象次数:14384
Heap
 def new generation   total 629120K, used 544520K [0x0000000740000000, 0x000000076aaa0000, 0x000000076aaa0000)
  eden space 559232K,  84% used [0x0000000740000000, 0x000000075cf82218, 0x0000000762220000)
  from space 69888K,  99% used [0x0000000762220000, 0x000000076665fff0, 0x0000000766660000)
  to   space 69888K,   0% used [0x0000000766660000, 0x0000000766660000, 0x000000076aaa0000)
 tenured generation   total 1398144K, used 708086K [0x000000076aaa0000, 0x00000007c0000000, 0x00000007c0000000)
   the space 1398144K,  50% used [0x000000076aaa0000, 0x0000000795e1d888, 0x0000000795e1da00, 0x00000007c0000000)
 Metaspace       used 2723K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 297K, capacity 386K, committed 512K, reserved 1048576K
Heap
 def new generation   total 629120K, used 367227K [0x0000000740000000, 0x000000076aaa0000, 0x000000076aaa0000)
  eden space 559232K,  53% used [0x0000000740000000, 0x000000075225ed18, 0x0000000762220000)
  from space 69888K,  99% used [0x0000000762220000, 0x000000076665fff8, 0x0000000766660000)
  to   space 69888K,   0% used [0x0000000766660000, 0x0000000766660000, 0x000000076aaa0000)
 tenured generation   total 1398144K, used 704670K [0x000000076aaa0000, 0x00000007c0000000, 0x00000007c0000000)
   the space 1398144K,  50% used [0x000000076aaa0000, 0x0000000795ac7988, 0x0000000795ac7a00, 0x00000007c0000000)
 Metaspace       used 2723K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 297K, capacity 386K, committed 512K, reserved 1048576K
```
```
#并行
cutiedeMacBook-Pro:第二周 cutie$ java -XX:+UseParallelGC -Xms2g -Xmx2g -XX:+PrintGCDetails GCLogAnalysis
正在执行...
[GC (Allocation Failure) [PSYoungGen: 524800K->87039K(611840K)] 524800K->150118K(2010112K), 0.0433302 secs] [Times: user=0.06 sys=0.31, real=0.05 secs] 
[GC (Allocation Failure) [PSYoungGen: 611839K->87031K(611840K)] 674918K->263232K(2010112K), 0.0571009 secs] [Times: user=0.08 sys=0.42, real=0.05 secs] 
[GC (Allocation Failure) [PSYoungGen: 611831K->87034K(611840K)] 788032K->383476K(2010112K), 0.0508760 secs] [Times: user=0.13 sys=0.30, real=0.05 secs] 
[GC (Allocation Failure) [PSYoungGen: 611834K->87039K(611840K)] 908276K->499305K(2010112K), 0.0511026 secs] [Times: user=0.13 sys=0.30, real=0.05 secs] 
[GC (Allocation Failure) [PSYoungGen: 611839K->87025K(611840K)] 1024105K->615164K(2010112K), 0.0498869 secs] [Times: user=0.13 sys=0.30, real=0.05 secs] 
[GC (Allocation Failure) [PSYoungGen: 611825K->87028K(327168K)] 1139964K->723181K(1725440K), 0.0487027 secs] [Times: user=0.13 sys=0.29, real=0.05 secs] 
[GC (Allocation Failure) [PSYoungGen: 327156K->147749K(388096K)] 963309K->788506K(1786368K), 0.0141585 secs] [Times: user=0.13 sys=0.01, real=0.02 secs] 
[GC (Allocation Failure) [PSYoungGen: 387877K->177268K(468480K)] 1028634K->833056K(1866752K), 0.0195749 secs] [Times: user=0.14 sys=0.04, real=0.02 secs] 
[GC (Allocation Failure) [PSYoungGen: 412788K->189173K(465920K)] 1068576K->874705K(1864192K), 0.0274799 secs] [Times: user=0.16 sys=0.08, real=0.03 secs] 
[GC (Allocation Failure) [PSYoungGen: 424693K->139161K(463872K)] 1110225K->912193K(1862144K), 0.0355604 secs] [Times: user=0.08 sys=0.22, real=0.04 secs] 
执行结束!共生成对象次数:15466
Heap
 PSYoungGen      total 463872K, used 148673K [0x0000000795580000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 237056K, 4% used [0x0000000795580000,0x0000000795ec9e38,0x00000007a3d00000)
  from space 226816K, 61% used [0x00000007b2280000,0x00000007baa667d0,0x00000007c0000000)
  to   space 230912K, 0% used [0x00000007a3d00000,0x00000007a3d00000,0x00000007b1e80000)
 ParOldGen       total 1398272K, used 773031K [0x0000000740000000, 0x0000000795580000, 0x0000000795580000)
  object space 1398272K, 55% used [0x0000000740000000,0x000000076f2e9f58,0x0000000795580000)
 Metaspace       used 2723K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 297K, capacity 386K, committed 512K, reserved 1048576K
```
```
#CMS
cutiedeMacBook-Pro:第二周 cutie$ java -XX:+UseConcMarkSweepGC -Xms2g -Xmx2g -XX:+PrintGCDetails GCLogAnalysis
正在执行...
[GC (Allocation Failure) [ParNew: 559232K->69888K(629120K), 0.0437960 secs] 559232K->147848K(2027264K), 0.0438284 secs] [Times: user=0.06 sys=0.32, real=0.04 secs] 
[GC (Allocation Failure) [ParNew: 629120K->69888K(629120K), 0.0478325 secs] 707080K->273728K(2027264K), 0.0478603 secs] [Times: user=0.11 sys=0.31, real=0.04 secs] 
[GC (Allocation Failure) [ParNew: 629120K->69887K(629120K), 0.0598118 secs] 832960K->399060K(2027264K), 0.0598374 secs] [Times: user=0.56 sys=0.03, real=0.06 secs] 
[GC (Allocation Failure) [ParNew: 629119K->69888K(629120K), 0.0630557 secs] 958292K->532640K(2027264K), 0.0630815 secs] [Times: user=0.59 sys=0.03, real=0.06 secs] 
[GC (Allocation Failure) [ParNew: 629120K->69886K(629120K), 0.0572034 secs] 1091872K->653594K(2027264K), 0.0572305 secs] [Times: user=0.54 sys=0.02, real=0.06 secs] 
[GC (Allocation Failure) [ParNew: 629118K->69887K(629120K), 0.0564769 secs] 1212826K->774446K(2027264K), 0.0565026 secs] [Times: user=0.53 sys=0.03, real=0.05 secs] 
[GC (CMS Initial Mark) [1 CMS-initial-mark: 704558K(1398144K)] 785656K(2027264K), 0.0002541 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.003/0.003 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.002/0.002 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
[CMS-concurrent-abortable-preclean-start]
[GC (Allocation Failure) [ParNew[CMS-concurrent-abortable-preclean: 0.002/0.103 secs] [Times: user=0.51 sys=0.02, real=0.10 secs] 
: 629119K->69887K(629120K), 0.0592415 secs] 1333678K->898638K(2027264K), 0.0592665 secs] [Times: user=0.55 sys=0.03, real=0.06 secs] 
[GC (CMS Final Remark) [YG occupancy: 69959 K (629120 K)][Rescan (parallel) , 0.0004432 secs][weak refs processing, 0.0000167 secs][class unloading, 0.0001841 secs][scrub symbol table, 0.0002320 secs][scrub string table, 0.0001405 secs][1 CMS-remark: 828751K(1398144K)] 898710K(2027264K), 0.0010608 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.005/0.005 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
执行结束!共生成对象次数:15665
Heap
 par new generation   total 629120K, used 280100K [0x0000000740000000, 0x000000076aaa0000, 0x000000076aaa0000)
  eden space 559232K,  37% used [0x0000000740000000, 0x000000074cd49438, 0x0000000762220000)
  from space 69888K,  99% used [0x0000000766660000, 0x000000076aa9fd60, 0x000000076aaa0000)
  to   space 69888K,   0% used [0x0000000762220000, 0x0000000762220000, 0x0000000766660000)
 concurrent mark-sweep generation total 1398144K, used 403613K [0x000000076aaa0000, 0x00000007c0000000, 0x00000007c0000000)
 Metaspace       used 2723K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 297K, capacity 386K, committed 512K, reserved 1048576K
```
```
#G1
cutiedeMacBook-Pro:第二周 cutie$ java -XX:+UseG1GC -Xms2g -Xmx2g -XX:+PrintGC GCLogAnalysis
正在执行...
[GC pause (G1 Evacuation Pause) (young) 126M->42M(2048M), 0.0095140 secs]
[GC pause (G1 Evacuation Pause) (young) 156M->79M(2048M), 0.0080826 secs]
[GC pause (G1 Evacuation Pause) (young) 193M->116M(2048M), 0.0083649 secs]
[GC pause (G1 Evacuation Pause) (young) 237M->150M(2048M), 0.0076123 secs]
[GC pause (G1 Evacuation Pause) (young) 267M->181M(2048M), 0.0078117 secs]
[GC pause (G1 Evacuation Pause) (young) 361M->229M(2048M), 0.0132963 secs]
[GC pause (G1 Evacuation Pause) (young) 414M->286M(2048M), 0.0154354 secs]
[GC pause (G1 Evacuation Pause) (young) 491M->332M(2048M), 0.0162206 secs]
[GC pause (G1 Evacuation Pause) (young) 976M->471M(2048M), 0.0400545 secs]
[GC pause (G1 Evacuation Pause) (young) 708M->520M(2048M), 0.0109296 secs]
[GC pause (G1 Evacuation Pause) (young) 926M->598M(2048M), 0.0200842 secs]
[GC pause (G1 Evacuation Pause) (young) 1087M->690M(2048M), 0.0261635 secs]
[GC pause (G1 Evacuation Pause) (young) 1240M->767M(2048M), 0.0260054 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 1213M->853M(2048M), 0.0240167 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0001518 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0036598 secs]
[GC remark, 0.0012069 secs]
[GC cleanup 882M->663M(2048M), 0.0010948 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0001242 secs]
执行结束!共生成对象次数:15678
```

**2.**使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。

**3.（选做）** 如果自己本地有可以运行的项目，可以按照 2 的方式进行演练。

**4.（必做）** 根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 的总结，提交到 Github。
##### 串行
要是为单线程环境（例如32位或Windows）和小型堆而设计的，会在其工作时冻结所有应用程序线程
##### 并行
优点是使用多个线程来扫描并压缩堆。 并行收集器的不利之处在于，在执行次要或完全GC收集时，它将停止应用程序线程。 并行收集器最适合可以容忍应用程序暂停并试图优化以减少由收集器引起的CPU开销的应用程序
##### CMS
该算法使用多个线程（“并发”）在堆（“标记”）中进行扫描以查找可以回收（“清扫”）的未使用对象。
两种情况下，该算法将进入STW模式：初始化根的初始标记（从线程入口点或静态变量可以访问的旧代对象），并且应用程序更改了状态在算法同时运行时将其堆放，迫使其返回并进行最后的修改以确保标记了正确的对象。
使用此收集器时，最大的问题是遇到升级失败 ，这是在收集年轻一代和老一代之间出现种族状况的情况。 如果收集器需要将年轻物体提升给老一代，但又没有足够的时间清理空间，则必须首先这样做，这将导致完整的STW收集-这就是CMS收集器的初衷。 为确保不会发生这种情况，您可以增加旧一代的大小（或为此增加整个堆的大小），或者为收集器分配更多的后台线程，以供他与对象分配率竞争。
与并行收集器相比，它的另一个缺点是，它使用更多的CPU，以便通过使用多个线程来执行扫描和收集，从而为应用程序提供更高级别的连续吞吐量。 对于大多数长时间运行的服务器应用程序而言，这不利于应用程序冻结，这通常是一个不错的选择。 即使这样， 默认情况下也不会启用该算法。 您必须指定XX：+ USeParNewGC才能真正启用它。 如果您愿意分配更多的CPU资源以避免应用程序暂停，那么这可能是您可能要使用的收集器，假设您的堆大小小于4Gb。 但是，如果大于4GB，则可能要使用最后一种算法-G1收集器。
##### G1
G1旨在更好地支持大于4GB的堆。 G1收集器利用多个后台线程来扫描它划分为多个区域的堆，范围从1MB到32MB（取决于堆的大小）。 G1收集器旨在首先扫描那些包含最多垃圾对象的区域，并为其标记（垃圾优先）。
这种策略有可能在后台线程完成扫描未使用的对象之前耗尽堆，在这种情况下，收集器将不得不停止应用程序，这将导致STW。 G1还具有另一个优势，即它可以在移动过程中压缩堆，这是CMS收集器仅在完整STW收集期间执行的操作。

**Week02 作业题目（周六）：**

**1.（选做）**运行课上的例子，以及 Netty 的例子，分析相关现象。
**2.（必做）**写一段代码，使用 HttpClient 或 OkHttp 访问 [http://localhost:8801](http://localhost:8801/)，代码提交到 Github。
```
import okhttp3.*;

import java.io.IOException;

public class OkHttpRequest {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String URL = "http://localhost:8088/api/hello";

    public static void main(String[] args) {
        try {
            String result = new OkHttpRequest().get(URL);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    OkHttpClient client = new OkHttpClient();
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}

```
