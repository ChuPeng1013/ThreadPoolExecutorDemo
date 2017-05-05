package com.example.administrator.threadpoolexecutordemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
{
    private Button ThreadPoolExecutorButton;
    private Button FixedThreadPoolButton;
    private Button CachedThreadPoolButton;
    private Button ScheduledThreadPoolButton;
    private Button SingleThreadPoolButton;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ThreadPoolExecutorButton = (Button) findViewById(R.id.ThreadPoolExecutor);
        FixedThreadPoolButton = (Button) findViewById(R.id.FixedThreadPool);
        CachedThreadPoolButton = (Button) findViewById(R.id.CachedThreadPool);
        ScheduledThreadPoolButton = (Button) findViewById(R.id.ScheduledThreadPool);
        SingleThreadPoolButton = (Button) findViewById(R.id.SingleThreadPool);

        ThreadPoolExecutorButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                ThreadPoolExecutor threadPoolExecutor =
                        new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(128));
                for(int i = 0; i < 30; i++)
                {
                    final int num = i;
                    Runnable runnable = new Runnable()
                    {
                        public void run()
                        {
                            try
                            {
                                Thread.sleep(2000);
                            }
                            catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                            Log.d("ThreadPoolExecutor", "Thread:" + num);
                        }
                    };
                    threadPoolExecutor.execute(runnable);
                }
            }
        });

        FixedThreadPoolButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
                for(int i = 0; i < 30; i++)
                {
                    final int num = i;
                    Runnable runnable = new Runnable()
                    {
                        public void run()
                        {
                            try
                            {
                                Thread.sleep(2000);
                            }
                            catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                            Log.d("FixedThreadPool", "Thread:" + num);
                        }
                    };
                    fixedThreadPool.execute(runnable);
                }
            }
        });

        CachedThreadPoolButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
                for(int i = 0; i < 30; i++)
                {
                    final int num = i;
                    Runnable runnable = new Runnable()
                    {
                        public void run()
                        {
                            try
                            {
                                Thread.sleep(2000);
                            }
                            catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                            Log.d("CachedThreadPool", "Thread:" + num + "——" + Thread.currentThread().getName());
                        }
                    };
                    cachedThreadPool.execute(runnable);
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

        ScheduledThreadPoolButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
                Runnable runnable = new Runnable()
                {
                    public void run()
                    {
                        Log.d("ScheduledThreadPool", "Thread");
                    }
                };
                scheduledExecutorService.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
            }
        });

        SingleThreadPoolButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
                for(int i = 0; i < 30; i++)
                {
                    final int num = i;
                    Runnable runnable = new Runnable()
                    {
                        public void run()
                        {
                            try
                            {
                                Thread.sleep(2000);
                            }
                            catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                            Log.d("SingleThreadPool", "Thread:" + num);
                        }
                    };
                    singleThreadPool.execute(runnable);
                }
            }
        });
    }
}