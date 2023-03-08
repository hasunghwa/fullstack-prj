import React, { useEffect, useState } from 'react';

import { Link } from 'react-router-dom';
import { Button } from 'antd';
import TableComponent from '@/components/TableComponent';
import MainLayout from '@/Layout/MainLayout';
import PostService from '@/api/PostService';

const Home = () => {
  const [posts, setPosts] = useState();

  const getPosts = async () => {
    const response = await PostService.getPosts();
    setPosts(response);
  };

  useEffect(() => {
    if (!posts) {
      getPosts();
    }
  }, [posts]);

  return (
    <MainLayout title="Home">
      <TableComponent posts={posts} />
      <Link to="/post/add">
        <Button type="primary" htmlType="submit">
          작성하기
        </Button>
      </Link>
    </MainLayout>
  );
};

export default Home;
