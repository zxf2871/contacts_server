package com.sectong.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.sectong.domain.ResetPasswordForm;
import com.sectong.domain.User;
import com.sectong.domain.UserCreateForm;

/**
 * 用户服务网接口定义
 * 
 * @author jiekechoo
 *
 */
public interface UserService {

	User create(UserCreateForm form);

	User getUserByUsername(String username);

	Object uploadImage(MultipartFile file, HttpServletRequest request);

	User getCurrentUser();

	String getCurrentUsername();

	Object listAllUsers(Pageable p);

	Object getUserList(int current, int rowCount, String searchPhrase);

	User resetPassword(ResetPasswordForm form);

}
