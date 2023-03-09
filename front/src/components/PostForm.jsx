import React from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import FormComponent from './FormComponent';
import PostService from '@/api/PostService';

const PostForm = () => {
  const { postId } = useParams();
  const navigate = useNavigate();

  const onsubmit = async values => {
    const { data } = await PostService.addPost({
      writer: values.user.name,
      title: values.user.title,
      contents: values.user.desc,
      postId,
    });

    navigate('/');
  };

  return <FormComponent isPost onSubmit={onsubmit} />;
};

export default PostForm;
