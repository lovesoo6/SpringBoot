package com.study.springboot.entity;

public class Emp {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp.emp_id
     *
     * @mbggenerated Sun Aug 21 14:24:16 CST 2022
     */
    private Integer empId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp.emp_name
     *
     * @mbggenerated Sun Aug 21 14:24:16 CST 2022
     */
    private String empName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp.emp_position
     *
     * @mbggenerated Sun Aug 21 14:24:16 CST 2022
     */
    private String empPosition;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp.login_account
     *
     * @mbggenerated Sun Aug 21 14:24:16 CST 2022
     */
    private String loginAccount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp.login_password
     *
     * @mbggenerated Sun Aug 21 14:24:16 CST 2022
     */
    private String loginPassword;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp.emp_id
     *
     * @return the value of t_emp.emp_id
     *
     * @mbggenerated Sun Aug 21 14:24:16 CST 2022
     */
    public Integer getEmpId() {
        return empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp.emp_id
     *
     * @param empId the value for t_emp.emp_id
     *
     * @mbggenerated Sun Aug 21 14:24:16 CST 2022
     */
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp.emp_name
     *
     * @return the value of t_emp.emp_name
     *
     * @mbggenerated Sun Aug 21 14:24:16 CST 2022
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp.emp_name
     *
     * @param empName the value for t_emp.emp_name
     *
     * @mbggenerated Sun Aug 21 14:24:16 CST 2022
     */
    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp.emp_position
     *
     * @return the value of t_emp.emp_position
     *
     * @mbggenerated Sun Aug 21 14:24:16 CST 2022
     */
    public String getEmpPosition() {
        return empPosition;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp.emp_position
     *
     * @param empPosition the value for t_emp.emp_position
     *
     * @mbggenerated Sun Aug 21 14:24:16 CST 2022
     */
    public void setEmpPosition(String empPosition) {
        this.empPosition = empPosition == null ? null : empPosition.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp.login_account
     *
     * @return the value of t_emp.login_account
     *
     * @mbggenerated Sun Aug 21 14:24:16 CST 2022
     */
    public String getLoginAccount() {
        return loginAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp.login_account
     *
     * @param loginAccount the value for t_emp.login_account
     *
     * @mbggenerated Sun Aug 21 14:24:16 CST 2022
     */
    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount == null ? null : loginAccount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp.login_password
     *
     * @return the value of t_emp.login_password
     *
     * @mbggenerated Sun Aug 21 14:24:16 CST 2022
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp.login_password
     *
     * @param loginPassword the value for t_emp.login_password
     *
     * @mbggenerated Sun Aug 21 14:24:16 CST 2022
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empPosition='" + empPosition + '\'' +
                ", loginAccount='" + loginAccount + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                '}';
    }

    public Emp() {
    }

    public Emp(Integer empId, String empName, String empPosition, String loginAccount, String loginPassword) {
        this.empId = empId;
        this.empName = empName;
        this.empPosition = empPosition;
        this.loginAccount = loginAccount;
        this.loginPassword = loginPassword;
    }
}