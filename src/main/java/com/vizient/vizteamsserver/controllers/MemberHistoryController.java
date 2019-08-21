package com.vizient.vizteamsserver.controllers;

import com.vizient.vizteamsserver.interfaces.MemberHistoryInterface;
import com.vizient.vizteamsserver.models.MemberHistory;
import com.vizient.vizteamsserver.services.MemberHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class MemberHistoryController implements MemberHistoryInterface {
    private MemberHistoryService memberHistoryService;

    public MemberHistoryController(MemberHistoryService memberHistoryService) {
        this.memberHistoryService = memberHistoryService;
    }

    @Override
    public ResponseEntity<?> getAllForMember(Long id) {
        try {
            List<MemberHistory> allByMemberId = memberHistoryService.getAllByMemberId(id);
            if (allByMemberId.size() > 0) {
                return new ResponseEntity<>(allByMemberId, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getAllForTeam(Long id) {
        try {
            List<MemberHistory> allByTeamId = memberHistoryService.getAllByTeamId(id);
            if (allByTeamId.size() > 0) {
                return new ResponseEntity<>(allByTeamId, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
