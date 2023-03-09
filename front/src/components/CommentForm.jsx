import React from 'react';
import { useParams } from 'react-router-dom';
import CommentService from '@/api/CommentService';
import FormComponent from './FormComponent';

const CommentForm = ({ setComments }) => {
  const { postId } = useParams();

  const onsubmit = async values => {
    const { data } = await CommentService.addComment({
      writer: values.user.name,
      contents: values.user.desc,
      postId,
    });

    setComments(prev => [...prev, data]);
  };

  return <FormComponent onSubmit={onsubmit} />;
};

export default CommentForm;
