import React from 'react';

import { Breadcrumb, Layout } from 'antd';
import MainHeader from './Header';
import MainFooter from './Footer';

const { Content } = Layout;

const MainLayout = ({ children, title }) => {
  return (
    <Layout>
      <MainHeader />
      <Content
        className="site-layout"
        style={{
          padding: '0px 50px',
        }}
      >
        <Breadcrumb
          style={{
            margin: '16px 0',
          }}
        >
          <Breadcrumb.Item>{title}</Breadcrumb.Item>
        </Breadcrumb>
        <div
          style={{
            padding: 24,
            minHeight: 600,
            background: 'white',
          }}
        >
          {children}
        </div>
      </Content>
      <MainFooter />
    </Layout>
  );
};

export default MainLayout;
