import React from 'react';
import { Button, Form, Input } from 'antd';

const FormComponent = ({ isPost, onSubmit }) => {
  const onFinish = async values => {
    onSubmit(values);
  };

  return (
    <Form name="nest-messages" onFinish={onFinish} style={{ width: '100%' }}>
      <Form.Item name={['user', 'name']} style={{ width: '120px' }}>
        <Input placeholder="이름" />
      </Form.Item>
      {isPost && (
        <Form.Item name={['user', 'title']} style={{ width: '120px' }}>
          <Input placeholder="제목" />
        </Form.Item>
      )}
      <Form.Item name={['user', 'desc']}>
        <Input.TextArea style={{ height: `${65 + isPost * 200}px` }} placeholder="내용" />
      </Form.Item>
      <Form.Item wrapperCol={{ offset: 0 }}>
        <Button type="primary" htmlType="submit">
          작성하기
        </Button>
      </Form.Item>
    </Form>
  );
};

export default FormComponent;
