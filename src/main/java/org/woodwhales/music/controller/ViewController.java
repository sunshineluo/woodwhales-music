package org.woodwhales.music.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.woodwhales.music.model.MusicInfo;
import org.woodwhales.music.service.music.MusicService;

import java.util.List;

/**
 * 视图控制器
 * @author woodwhales
 */
@Slf4j
@Controller
public class ViewController {

	@Autowired
	private MusicService musicService;

	@GetMapping({"", "index"})
	public String index(Model model) {
		List<MusicInfo> musicInfos = musicService.listMusic();

		for (MusicInfo musicInfo : musicInfos) {
			musicInfo.setMp3(StringUtils.replace(musicInfo.getMp3(), "https://cdn.jsdelivr.net/", "https://fastly.jsdelivr.net/"));
			musicInfo.setCover(StringUtils.replace(musicInfo.getCover(), "https://cdn.jsdelivr.net/", "https://fastly.jsdelivr.net/"));
		}

		model.addAttribute("musicInfoList", musicInfos);
		return "index";
	}
	
}
