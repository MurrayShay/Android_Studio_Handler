Android Handler 執行緒教學
==============================
####★UI執行緒和工作執行緒不能跨執行緒溝通，<br/>因此Android提供許多方式讓WorkerThread去存取UI執行緒。
(1)Activity.runOnUiThread(Runnable)   
(2)View.post(Runnable)    
(3)View.postDelayed(Runnable,long)    
(4)Handler    
(5)AsyncTask    
在此說明 Handler 使用規則
---------------------------------
####★Outline
* 執行緒間通訊(如:worker執行緒將UI更新程式片段傳遞給UI執行緒)，可以是**資料傳遞**或是**方法的傳遞**。
* 每一個執行緒都可以有一個訊息佇列(Message Queue)和一個循環器(Looper)，在Android中建立的UI執行緒具有**訊息佇列**和**一個循環器**，其它worker執行緒預設並沒有(可使用HandlerThread產生具有訊息佇列和循環器的工作執行緒)。
* Handler,Message,Message,Lopper四個類別就是讓資料、程式碼，可以丟到UI(其他)執行緒中執行。

#####★Message 類別
> 記錄要在執行緒間傳遞的資料， 透過Handler可將Message傳遞給Handler所在執行緒的MessageQueue中。

#####★MessageQueue 類別
> 用來存放Message，依照先進先出FIFO原則存放Message。

#####★Looper 類別
> 循環器，MessageQueue的管理者，為一個無窮迴圈，不斷的從MessageQueue中取出Message，並啟動執行此Message所指向的Handler中的回乎方法。

#####★Handler 類別
> 處理者，負責Message的發送和Message內容的執行處理。    
> Handler會透過兩種方式向MessageQueue發送訊息，兩種皆會訊息放入MessageQueue的尾端，按先進先出執行。
>> sendMessage方法:發送一個Message到Handler所在執行緒中的MessageQueue中，當Message備取出時，會由Handler的handleMessage()方法處理。    
>> Post方法:發送一個runnable物件到MessageQueue中，當runnable物件被取出時，會在Handler所在的執行緒中備執行。

* * * *
相關 API 解釋
-------------------------------
####★Message 類別
###### Message 類別的欄位:
``` JAVA
       int arg1 //欲傳遞的整數值1 
       int arg2 //欲傳遞的整數值2   
       Object obj //欲傳遞的物件
       Messenger replyTo //提供接收端回覆的Messenger
       int what //自行定義的訊息碼，可供Handler判斷訊息用
```
####★Handler 類別
###### 鍵構子
``` JAVA
       Handler() //目前Thread的looper
       Handler(Looper looper) //指定looper
```
###### 方法**(資料傳遞)**
``` JAVA
       void handlerMessage(Message msg) //處理looper所管理的MessageQueue
       boolean sendMessage(Message msg)
       boolean sendMessageAtFrontOfQueue(Message msg)
       boolean sendMessageAtTime(Message msg, long uptimeMillis)
       boolean sendMessageDelayed(Message msg, long delayMillis)
       void removeMessages(int what) //將MessageQueue中的Message.what與參數what相符的訊息移除
```
###### 方法**(方法的傳遞)**
``` JAVA
       boolean post(Runnable r)
       boolean postAtFrontOfQueue(Runnable r) //將Runnable物件r放入MessageQueue的最前方
       boolean postAtTime(Runnable r, Object token, long uptimeMillis)
       boolean postAtTime(Runnable r, long uptimeMillis) //將Runnable物件r放入MessageQueue中,當時間到達參數uptimeMillis指定時間時，此Runnable物件會被執行
       boolean postDelayed(Runnable r, long delayMillis) //將Runnable物件r放入MessageQueue中,延遲參數delayMillis指定時間時，此Runnable物件會被執行
       void removeCallbacks(Runnable r) //將MessageQueue中的Runnable物件r移除
```
###### 方法*(從loopper的MessageQueue取得一個Message Object)*
``` JAVA
       Message obtainMessage() //取得MessageQueue中，可用的Message物件
       Message obtainMessage(int what, int arg1, int arg2) //參數arg1，設定Message物件中的arg1屬性值
       Message obtainMessage(int what, int arg1, int arg2,Object obj) //參數arg2，設定Message物件中的arg2屬性值
       Message obtainMessage(int what) //參數what，設定Message物件中的what屬性值
       Message obtainMessage(int what, Object obj) //參數obj，設定Message物件中的obj屬性值
````
