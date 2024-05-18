import React, {useEffect, useState} from 'react';
import axios from 'axios';
import { Form, Button, Container } from 'react-bootstrap';

axios.interceptors.request.use(request => {
  console.log('Starting Request', JSON.stringify(request, null, 2));
  return request;
});

function CreatePostPage() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [status, setStatus] = useState("");
  const [location, setLocation] = useState("");
  const [userId, setUserId] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [categoryId, setCategoryId] = useState("");

  const [categories, setCategories] = useState([]);
  const validStatuses = ["ACTIVE", "INACTIVE", "SOLD", "EXPIRED"];
  const statusOptions = [...validStatuses];
  statusOptions.unshift("Select Status")


  //Init Categories
  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await axios.get('http://localhost:8080/category/getAll');
        response.data.unshift({id: -1, name: "Select Category"})
        setCategories(response.data);
        setCategoryId(-1);
      } catch (error) {
        console.error('There was an error!', error);
      }
    };

    fetchCategories();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!validStatuses.includes(status)) {
      alert("Please select a status");
      return;
    }

    if (categoryId === -1) {
      alert("Please select a category");
      return;
    }

    const post = {
      title,
      description,
      status,
      location,
      categoryId,
      userId,
      phoneNumber
    };

    try {
      const response = await axios.post('http://localhost:8080/post/create', post);
      console.log(response.data);
    } catch (error) {
      console.error('There was an error!', error);
    }
  };

  return (
    <Container>
      <h1>Create Post Page</h1>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="formTitle">
          <Form.Label>Title</Form.Label>
          <Form.Control type="text" value={title} onChange={e => setTitle(e.target.value)} />
        </Form.Group>

        <Form.Group controlId="formDescription">
          <Form.Label>Description</Form.Label>
          <Form.Control type="text" value={description} onChange={e => setDescription(e.target.value)} />
        </Form.Group>

        <Form.Group controlId="formLocation">
          <Form.Label>Location</Form.Label>
          <Form.Control type="text" value={location} onChange={e => setLocation(e.target.value)} />
        </Form.Group>

        <Form.Group controlId="formStatus">
          <Form.Label>Status</Form.Label>
          <Form.Select value={status} onChange={e => setStatus(e.target.value)}>
            {statusOptions.map(option => (
              <option key={option} value={option}>{option}</option>
            ))}
          </Form.Select>
        </Form.Group>

        <Form.Group controlId="formCategoryId">
          <Form.Label>Category</Form.Label>
          <Form.Select value={categoryId} onChange={e => setCategoryId(e.target.value)}>
            {categories.map(category => (
              <option key={category.id} value={category.id}>{category.name}</option>
            ))}
          </Form.Select>
        </Form.Group>

        <Form.Group controlId="formUserId">
          <Form.Label>User ID</Form.Label>
          <Form.Control type="text" value={userId} onChange={e => setUserId(e.target.value)} />
        </Form.Group>

        <Form.Group controlId="formPhoneNumber">
          <Form.Label>Phone Number</Form.Label>
          <Form.Control type="text" value={phoneNumber} onChange={e => setPhoneNumber(e.target.value)} />
        </Form.Group>

        <Button variant="primary" type="submit">
          Create Post
        </Button>
      </Form>
    </Container>
  );
}

export default CreatePostPage;