package com.ti.crowd_manager.domain;

import java.io.Serializable;

public class ItemDetail implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_detail.id
     *
     * @mbg.generated Sun Jan 27 13:43:57 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_detail.item_id
     *
     * @mbg.generated Sun Jan 27 13:43:57 CST 2019
     */
    private Integer itemId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_detail.content
     *
     * @mbg.generated Sun Jan 27 13:43:57 CST 2019
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_detail.id
     *
     * @return the value of item_detail.id
     *
     * @mbg.generated Sun Jan 27 13:43:57 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_detail.id
     *
     * @param id the value for item_detail.id
     *
     * @mbg.generated Sun Jan 27 13:43:57 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_detail.item_id
     *
     * @return the value of item_detail.item_id
     *
     * @mbg.generated Sun Jan 27 13:43:57 CST 2019
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_detail.item_id
     *
     * @param itemId the value for item_detail.item_id
     *
     * @mbg.generated Sun Jan 27 13:43:57 CST 2019
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_detail.content
     *
     * @return the value of item_detail.content
     *
     * @mbg.generated Sun Jan 27 13:43:57 CST 2019
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_detail.content
     *
     * @param content the value for item_detail.content
     *
     * @mbg.generated Sun Jan 27 13:43:57 CST 2019
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}