package com.obifyconsulting.cmsbe.service;

import com.obifyconsulting.cmsbe.dto.ErrorDTO;
import com.obifyconsulting.cmsbe.dto.CourseDTO;
import com.obifyconsulting.cmsbe.entity.Course;
import com.obifyconsulting.cmsbe.entity.User;
import com.obifyconsulting.cmsbe.exception.BusinessException;
import com.obifyconsulting.cmsbe.repository.CourseRepository;
import com.obifyconsulting.cmsbe.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public Long createCourse(CourseDTO courseDTO) {

        Optional<Course> optTask = courseRepository.findByTitleContains(courseDTO.getTitle());
        if(optTask.isPresent()){
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setCode("DUP_001");
            errorDTO.setMessage(String.format("Course with title %s already exist", courseDTO.getTitle()));
            List<ErrorDTO> errorDTOS = Arrays.asList(errorDTO);
            throw new BusinessException(errorDTOS);
        }

        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);
        course.setInstructor(getCurrentUser());
        course = courseRepository.save(course);
        return course.getId();
    }

    @Override
    public List<CourseDTO> getAllCourses() {

        List<Course> courses = courseRepository.findAll();
        List<CourseDTO> dtoList = new ArrayList<>();
        /**One by one convert every Entity to DTO and add to DTO list and finally return the DTO list**/
        courses.forEach((course)->{
            CourseDTO courseDTO = new CourseDTO();
            BeanUtils.copyProperties(course, courseDTO);
            dtoList.add(courseDTO);
        });
        return dtoList;
    }

    @Override
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public List<CourseDTO> getAllCoursesByInstructor() {

        User user = getCurrentUser();
        List<Course> courses = courseRepository.findAllByInstructorId(user.getId());
        List<CourseDTO> dtoList = new ArrayList<>();
        /**One by one convert every Entity to DTO and add to DTO list and finally return the DTO list**/
        courses.forEach((course)->{
            CourseDTO courseDTO = new CourseDTO();
            BeanUtils.copyProperties(course, courseDTO);
            dtoList.add(courseDTO);
        });
        return dtoList;
    }

    @Override
    public CourseDTO getCourse(Long courseId) {
        /**Check if the course exist or not, if course with this courseId does not exist then throw exception**/
        Course course = courseRepository.findById(courseId).orElseThrow(
                ()->{
                    ErrorDTO errorDTO = new ErrorDTO();
                    errorDTO.setCode("NOT_FOUND_002");
                    errorDTO.setMessage(String.format("Course with Id %d does not exist", courseId));
                    List<ErrorDTO> errorDTOS = Arrays.asList(errorDTO);
                    return new BusinessException(errorDTOS);
                }
        );
        /**If course found then convert entity to dto with the help of BeanUtils.copyProperties**/
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO);
        /**Return the converted DTO**/
        return courseDTO;
    }

    @Override
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public CourseDTO deleteCourse(Long courseId) {
        /**Check if the course exist or not, if course with this courseId does not exist then throw exception**/
        Course course = courseRepository.findById(courseId).orElseThrow(
                ()->{
                    ErrorDTO errorDTO = new ErrorDTO();
                    errorDTO.setCode("NOT_FOUND_002");
                    errorDTO.setMessage(String.format("Course with Id %d does not exist", courseId));
                    List<ErrorDTO> errorDTOS = Arrays.asList(errorDTO);
                    return new BusinessException(errorDTOS);
                }
        );
        /**If course found then 1st delete it after that convert entity to dto with the help of BeanUtils.copyProperties**/
        courseRepository.deleteById(course.getId());
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO);
        /**Return the converted DTO**/
        return courseDTO;
    }

    @Override
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public CourseDTO updateCourse(CourseDTO courseDTO) {
        /**Check if the course exist or not, if course with this courseId does not exist then throw exception**/
        Course course = courseRepository.findById(courseDTO.getId()).orElseThrow(
                ()->{
                    ErrorDTO errorDTO = new ErrorDTO();
                    errorDTO.setCode("NOT_FOUND_002");
                    errorDTO.setMessage(String.format("Course with Id %d does not exist", courseDTO.getId()));
                    List<ErrorDTO> errorDTOS = Arrays.asList(errorDTO);
                    return new BusinessException(errorDTOS);
                }
        );
        /**Convert the DTO to Entity**/
        BeanUtils.copyProperties(courseDTO, course);
        /**Save the converted Entity**/
        course = courseRepository.save(course);
        /**Convert the updated Entity to DTO again**/
        BeanUtils.copyProperties(course, courseDTO);
        /**Return the converted DTO**/
        return courseDTO;
    }

    @Override
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public CourseDTO updateCoursePrice(CourseDTO courseDTO) {
        /**Check if the course exist or not, if course with this courseId does not exist then throw exception**/
        Course course = courseRepository.findById(courseDTO.getId()).orElseThrow(
                ()->{
                    ErrorDTO errorDTO = new ErrorDTO();
                    errorDTO.setCode("NOT_FOUND_002");
                    errorDTO.setMessage(String.format("Course with Id %d does not exist", courseDTO.getId()));
                    List<ErrorDTO> errorDTOS = Arrays.asList(errorDTO);
                    return new BusinessException(errorDTOS);
                }
        );
        /**Set the new price inside Entity - example of partial update**/
        course.setPrice(courseDTO.getPrice());
        /**Save the converted Entity**/
        course = courseRepository.save(course);
        /**Convert the updated Entity to DTO again**/
        BeanUtils.copyProperties(course, courseDTO);
        /**Return the converted DTO**/
        return courseDTO;
    }

    @Override
    public List<CourseDTO> searchCourseByTitle(String title) {
        List<Course> courses = courseRepository.findAllByTitleContains(title);
        List<CourseDTO> dtoList = new ArrayList<>();
        /**One by one convert every Entity to DTO and add to DTO list and finally return the DTO list**/
        courses.forEach((course)->{
            CourseDTO courseDTO = new CourseDTO();
            BeanUtils.copyProperties(course, courseDTO);
            dtoList.add(courseDTO);
        });
        return dtoList;
    }

    public User getCurrentUser(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername()).get();
    }
}
