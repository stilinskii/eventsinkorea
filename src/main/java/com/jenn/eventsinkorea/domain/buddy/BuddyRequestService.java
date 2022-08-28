package com.jenn.eventsinkorea.domain.buddy;

import com.jenn.eventsinkorea.domain.admin.repository.UserRepository;
import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.domain.buddy.model.BuddyRequest;
import com.jenn.eventsinkorea.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuddyRequestService {
    private final BuddyRequestRepository buddyRequestRepository;
    private final BuddyRepository buddyRepository;
    private final UserRepository userRepository;


    public List<BuddyRequest> getSentRequestByUsername(String name){
        return buddyRequestRepository.findByUser(userRepository.findByUsername(name));
    }


    public List<BuddyRequest> getSentRequestByUsernameAndStatus(String name, Integer status, Integer reviewStatus){
        return buddyRequestRepository.findByUserAndStatusAndReview(userRepository.findByUsername(name),status,reviewStatus);
    }

    public void deleteRequest(Long buddyId,String username){
       buddyRequestRepository.deleteByUserAndBuddy(userRepository.findByUsername(username).getId(),buddyId);
    }

    public List<BuddyRequest> getReceivedRequestByUsername(String name, boolean waiting){
        Buddy buddy = buddyRepository.findByUser(userRepository.findByUsername(name)).orElse(null);
        if (waiting){
            return buddy==null? null :buddyRequestRepository.findByBuddyAndStatus(buddy,0);
        }else{
            return buddy==null? null :buddyRequestRepository.findByBuddyAndStatusNot(buddy,0);
        }
    }

    public BuddyRequest updateStatus(String buddyName, Long requesterId, int status) {
        User request_user = userRepository.getById(requesterId);
        Buddy buddy = buddyRepository.findByUser(userRepository.findByUsername(buddyName)).orElse(null);
        //업데이트와 동시에 버디가 삭제될 경우 대비
        if(buddy==null){
            return null;
        }

        BuddyRequest buddyRequest = buddyRequestRepository.findByBuddyAndUser(buddy, request_user).orElse(null);

        //업데이트와 동시에 리퀘스트가 삭제될 경우 대비
        if(buddyRequest==null){
            return null;
        }

        //성공로직
        //바꾸기 바라는 상태로 set 1: 수락 / 2: 거절
        buddyRequest.setStatus(status);
        BuddyRequest updatedReq = buddyRequestRepository.save(buddyRequest);

        //어떠한 이유로 업데이트가 제대로 안됐을때 null return
        return updatedReq.getStatus()==status?updatedReq:null;

    }
}
