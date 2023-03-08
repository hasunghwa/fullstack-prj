import { Table } from 'antd';
import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

const columns = [
  {
    title: '제목',
    dataIndex: 'title',
    render: (text, item) => <Link to={`/post/${item.key}`}>{text}</Link>,
    key: 'title',
  },
  {
    title: '작성자',
    dataIndex: 'writer',
    key: 'writer',
  },
  {
    title: '작성일',
    dataIndex: 'writeDate',
    key: 'writeDate',
  },
];

const TableComponent = ({ posts }) => {
  const [dataSource, setDataSource] = useState();

  useEffect(() => {
    if (posts && !dataSource) {
      const data = [];
      posts.forEach(post => {
        data.push({
          key: post.postId,
          title: post.title,
          writer: post.writer,
          writeDate: post.createDate,
        });
      });

      setDataSource(data);
    }
  }, [posts, dataSource]);

  return <Table dataSource={dataSource} columns={columns} rowKey={render => render.key} />;
};

export default TableComponent;
