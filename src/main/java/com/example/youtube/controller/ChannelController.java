package com.example.youtube.controller;

import com.example.youtube.dto.attach.AttachDTO;
import com.example.youtube.dto.channel.ChannelDTO;
import com.example.youtube.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @PostMapping("/private/create")
    public ResponseEntity<ChannelDTO> create(@RequestBody ChannelDTO dto) {
        return ResponseEntity.ok(channelService.create(dto));
    }
    @PutMapping("/private/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody ChannelDTO dto) {
        return ResponseEntity.ok(channelService.update(id,dto));
    }
    @PutMapping("/private/update_photo/{id}")
    public ResponseEntity<?> updatePhoto(@PathVariable("id") String id,
                                    @RequestBody ChannelDTO dto) {
        return ResponseEntity.ok(channelService.updatePhoto(id,dto));
    }
    @PutMapping("/private/update_banner/{id}")
    public ResponseEntity<?> updateBanner(@PathVariable("id") String id,
                                         @RequestBody ChannelDTO dto) {
        return ResponseEntity.ok(channelService.updateBanner(id,dto));
    }
    @GetMapping("/private/pagination/")
    public ResponseEntity<Page<ChannelDTO>> pagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "size", defaultValue = "4") int size){
        Page<ChannelDTO> pagination = channelService.paginationWithDate(page, size);
        return ResponseEntity.ok(pagination);
    }
    @GetMapping("/private/getById/{id}")
    public ResponseEntity<ChannelDTO> getById(@PathVariable("id") String id){
        return ResponseEntity.ok(channelService.getChanelById(id));
    }
    @PutMapping("/private/update_status/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable("id") String id) {
        return ResponseEntity.ok(channelService.updateStatus(id));
    }
}
