package com.example.youtube.service;

import com.example.youtube.repository.PlayListVideoRepository;
import com.example.youtube.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayListVideoService {
    private final PlayListVideoRepository playListVideoRepository;
    private final VideoRepository videoRepository;

    public Integer getTotalViewCountByPlayListId(Integer pId){
        Integer result=0;
        List<String> list = playListVideoRepository.getVideoIdListByPlaylistId(pId);
        for (String id : list) {
            Integer viewCount = videoRepository.getViewCount(id);
            result+=viewCount;
        }
        return result;
    }
}
