package com.example.mohit.chipz;

import android.app.job.JobParameters;
import android.app.job.JobService;

class MyJobService extends JobService {

    boolean isWorking = false;
    boolean jobCancelled = false;
    @Override
    public boolean onStartJob(JobParameters params) {

        isWorking = true ;
        startworkonnewthreads(params);
        return false;
    }

    public void startworkonnewthreads(final JobParameters parameters)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dowork(parameters);
            }
        });
    }

    public void dowork(JobParameters parameters)
    {
        for(int i= 0 ; i < 6000*60*24 ; i++)
        {
            if(jobCancelled)
            {
                return;
            }
            try
            {
                Thread.sleep(10);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean onStopJob(JobParameters params) {
        jobCancelled = true ;
        boolean needsReschedule = isWorking ;
        jobFinished(params , needsReschedule);
        return needsReschedule;
    }
}
