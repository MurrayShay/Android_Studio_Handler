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
