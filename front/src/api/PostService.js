import api from '@/api/apiConfig';

const PostService = {
  getPosts: async () => {
    const response = await api.get('/api/posts');
    return response.data;
  },

  getPostById: async postId => {
    const response = await api.get(`/api/post/${postId}`);
    return response.data;
  },

  addPost: async ({ writer, title, contents }) => {
    const response = await api.post('/api/post/add', {
      writer,
      title,
      contents,
    });

    return response;
  },

  updatePost: async ({ postId, title, contents }) => {
    const response = await api.post('/api/post/config', {
      postId,
      title,
      contents,
    });

    return response;
  },

  deletePost: async postId => {
    const response = await api.post('/api/post/delete', {
      postId,
    });

    return response;
  },
};

export default PostService;
