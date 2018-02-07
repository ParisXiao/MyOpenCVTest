LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)


OpenCV_INSTALL_MODULES := on
OpenCV_CAMERA_MODULES := off

OPENCV_LIB_TYPE :=STATIC

ifeq ("$(wildcard $(OPENCV_MK_PATH))","")
include ..\..\..\..\native\jni\OpenCV.mk
else
include $(OPENCV_MK_PATH)
endif

LOCAL_MODULE := opencv

LOCAL_SRC_FILES :=com_guanaida_myopencv_NativeGrabCut.cpp
LOCAL_LDLIBS +=  -lm -llog
  APP_STL         := gnustl_shared
include $(BUILD_SHARED_LIBRARY)