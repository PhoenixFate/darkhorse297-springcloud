package com.example.minio.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.example.minio.common.CommonConstants;
import com.example.minio.common.R;
import com.example.minio.service.MinioTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件管理
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/file")
public class FileController {
	private final MinioTemplate minioTemplate;

	/**
	 * 上传文件
	 * 文件名采用uuid,避免原始文件名中带"-"符号导致下载的时候解析出现异常
	 *
	 * @param file 资源
	 * @return R(bucketName, filename)
	 */
	@PostMapping("/upload")
	public R upload(@RequestParam("file") MultipartFile file) {
		String fileName = IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(file.getOriginalFilename());
		Map<String, String> resultMap = new HashMap<>(4);
		resultMap.put("bucketName", CommonConstants.BUCKET_NAME);
		resultMap.put("fileName", fileName);
		resultMap.put("path", "/file/" + CommonConstants.BUCKET_NAME + "-" + fileName);
		try {
			minioTemplate.putObject(CommonConstants.BUCKET_NAME, fileName, file.getInputStream());
		} catch (Exception e) {
			log.error("上传失败", e);
			return R.builder().code(CommonConstants.FAIL)
					.msg(e.getLocalizedMessage()).build();
		}
		return R.builder().data(resultMap).build();
	}


	/**
	 * 获取文件
	 *
	 * @param fileName 文件空间/名称
	 * @param response
	 * @return
	 */
	@GetMapping("/{fileName}")
	public void file(@PathVariable String fileName, HttpServletResponse response) {
		String[] nameArray = StrUtil.split(fileName, StrUtil.DASHED);

		try (InputStream inputStream = minioTemplate.getObject(nameArray[0], nameArray[1])) {
			response.setContentType("application/octet-stream; charset=UTF-8");
			IoUtil.copy(inputStream, response.getOutputStream());
		} catch (Exception e) {
			log.error("文件读取异常", e);
		}
	}
}
