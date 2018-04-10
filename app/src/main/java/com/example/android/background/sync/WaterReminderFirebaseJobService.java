/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.background.sync;


import android.content.Context;
import android.os.AsyncTask;


public class WaterReminderFirebaseJobService extends com.firebase.jobdispatcher.JobService {

    private AsyncTask mBackgroundTask;

    // TODO (3) WaterReminderFirebaseJobService should extend from JobService
    //COMPLETE

    // TODO (4) Override onStartJob
    //COMPLETE
        // TODO (5) By default, jobs are executed on the main thread, so make an anonymous class extending
        //  AsyncTask called mBackgroundTask.
        //COMPLETE
            // TODO (6) Override doInBackground
            //COMPLETE
                // TODO (7) Use ReminderTasks to execute the new charging reminder task you made, use
                // this service as the context (WaterReminderFirebaseJobService.this) and return null
                // when finished.
                //COMPLETE
            // TODO (8) Override onPostExecute and called jobFinished. Pass the job parameters
            // and false to jobFinished. This will inform the JobManager that your job is done
            // and that you do not want to reschedule the job.
            //COMPLETE

    @Override
    public boolean onStartJob(final com.firebase.jobdispatcher.JobParameters params) {
        mBackgroundTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Context context = WaterReminderFirebaseJobService.this;
                ReminderTasks.executeTask(context, ReminderTasks.ACTION_CHARGING_REMINDER);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(params, false);

            }
        };
        mBackgroundTask.execute();
        return true;
    }

        // TODO (9) Execute the AsyncTask
        //COMPLETE
        // TODO (10) Return true
        //COMPLETE

    // TODO (11) Override onStopJob
    //COMPLETE
        // TODO (12) If mBackgroundTask is valid, cancel it
        //COMPLETE
        // TODO (13) Return true to signify the job should be retried
        //COMPLETE

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters params) {
        if(mBackgroundTask != null){
            mBackgroundTask.cancel(true);
        }
        return true;
    }

}
