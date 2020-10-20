package org.feather.service;

import org.feather.pojo.Stu;

public interface StuService {
    public Stu getStuInfo(int id);

    public void saveStu();

    public void updateStu(int id);

    public void deleteStu(int id);

}
