package com.driver;

import org.apache.commons.lang3.tuple.Pair;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private final ArrayList<Meeting> calendar;

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE);
        this.calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // at a particular time, you can only be present in at most one meeting
        // if you want to attend a meeting, you must join it at its start time and leave at end time.

        ArrayList<Pair<LocalTime, Integer>> endTimes = new ArrayList<>();

        for (int i = 0; i < calendar.size(); i++) {
            endTimes.add(Pair.of(calendar.get(i).getEndTime(), i));
        }

        Collections.sort(endTimes);

        LocalTime time_limit = endTimes.get(0).getLeft();

        int cnt = 0;
        if(!endTimes.isEmpty()) {
            cnt += 1;
        }

        for (int i = 1; i < endTimes.size(); i++) {
            if (calendar.get(endTimes.get(i).getRight()).getStartTime().isAfter(time_limit)) {

                cnt += 1;
                time_limit = endTimes.get(i).getLeft();
            }
        }

        return cnt;
    }
}