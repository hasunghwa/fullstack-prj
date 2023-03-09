import api from '@/api/apiConfig';

const CommentService = {
  getCommentByPostId: async postId => {
    const response = await api.get(`/api/comment/${postId}`);
    return response.data;
  },

  addComment: async ({ writer, contents, postId }) => {
    const response = await api.post('/api/comment/add', {
      writer,
      contents,
      postId,
    });

    return response;
  },

  updateComment: async ({ contents, commentId }) => {
    const response = await api.post('/api/comment/config', {
      commentId,
      contents,
    });

    return response;
  },

  deleteComment: async commentId => {
    const response = await api.post('/api/comment/delete', {
      commentId,
    });

    return response;
  },
};

export default CommentService;
