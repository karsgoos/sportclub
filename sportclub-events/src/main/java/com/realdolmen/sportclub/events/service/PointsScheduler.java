package com.realdolmen.sportclub.events.service;

import com.realdolmen.sportclub.common.entity.Attendance;
import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.RegisteredUser;
import com.realdolmen.sportclub.common.entity.User;
import com.realdolmen.sportclub.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.HOURS;

@Component
public class PointsScheduler {
	
	@Autowired
	EventRepository eventRepository;
	
	//once a day check events and update points of users if the events started in the last day
	@Scheduled(initialDelay = 86400000, fixedDelay = 86400000)
	public void updatePoints(){
		List<Event> events = eventRepository.findAll();
		for(Event e : events){
			//startdate between yesterday and today
			if(e.getStartDate().isAfter(LocalDateTime.now().minusDays(1)) && e.getStartDate().isBefore(LocalDateTime.now())){
				List<Attendance> attendances = e.getAttendancies();
				
				//get distinct users that have not cancelled
				List<User> users = new ArrayList<>();
				for (Attendance a: attendances) {
					User u = a.getOrdr().getUser();
					if( !users.contains(u) && !a.isCancelled())
						users.add(u);
				}
				
				//add points to users
				for(User u : users){
					if(u.getClass()== RegisteredUser.class)
						((RegisteredUser)u).addPoints(e.getPoints());
				}
			}
		}
	}
	
	
}
