import { useEffect, useState } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import { Form, Button, Container, Alert } from "react-bootstrap";
import { useStore } from "../../stores/stores";

function EditPostPage() {
  const { postId } = useParams();
  const navigate = useNavigate();
  const { userStore } = useStore();

  const [initialData, setInitialData] = useState<any>({});
  const [title, setTitle] = useState(initialData?.title || "");
  const [description, setDescription] = useState(
    initialData?.description || ""
  );
  const [price, setPrice] = useState(initialData?.price || 0.0);
  const [status, setStatus] = useState(initialData?.status || "");
  const [location, setLocation] = useState(initialData?.location || "");
  const [categoryId, setCategoryId] = useState(initialData?.categoryId || "");
  const [images, setImages] = useState<File[]>([]);
  const [categories, setCategories] = useState([]);
  const [alertMessage, setAlertMessage] = useState("");

  useEffect(() => {
    const fetchPost = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/posts/${postId}`
        );
        const postData = response.data;
        setTitle(postData.title);
        setDescription(postData.description);
        setPrice(postData.price);
        setStatus(postData.status);
        setLocation(postData.location);
        setCategoryId(postData.categoryId);
        setImages(postData.imageUrl);
        setInitialData(postData);
      } catch (error) {
        console.error("There was an error fetching the post!", error);
      }
    };

    const fetchCategories = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/categories"
        );
        setCategories(response.data);
      } catch (error) {
        console.error("There was an error!", error);
      }
    };

    fetchPost();
    fetchCategories();
  }, [postId]);

  const handleUpdatePost = async (post: any) => {
    try {
      await axios.put(`http://localhost:8080/api/posts/edit/${postId}`, post, {
        headers: {
          "Content-Type": "application/json",
        },
      });
      navigate(`/posts/${postId}`);
    } catch (error) {
      console.error("There was an error updating the post!", error);
    }
  };
  const handleImageUpload = async (e: any) => {
    const files = Array.from(e.target.files) as File[];
    setImages(files);

    const formData = new FormData();

    for (let i = 0; i < files.length; i++) {
      formData.append("images", files[i]);
    }

    formData.append("postId", postId!);

    try {
      const response = await axios.post(
        "http://localhost:8080/api/images/upload",
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );
      console.log(response.data);
      setAlertMessage("Image uploaded successfully!");
    } catch (error) {
      console.error("There was an error uploading the image!", error);
      setAlertMessage("Failed to upload image. Please try again.");
    }
  };

  const handleSubmit = async (e: any) => {
    e.preventDefault();

    const post = {
      title,
      description,
      price,
      status,
      location,
      categoryId,
      userId: userStore.userId,
      images,
    };

    await handleUpdatePost(post);
  };

  return (
    <Container>
      <h1>Edit Post</h1>
      {alertMessage && (
        <Alert
          variant={alertMessage.includes("successfully") ? "success" : "danger"}
        >
          {alertMessage}
        </Alert>
      )}
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="formTitle">
          <Form.Label>Title</Form.Label>
          <Form.Control
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </Form.Group>
        <Form.Group controlId="formDescription">
          <Form.Label>Description</Form.Label>
          <Form.Control
            type="text"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />
        </Form.Group>
        <Form.Group controlId="formPrice">
          <Form.Label>Price</Form.Label>
          <Form.Control
            type="number"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
          />
        </Form.Group>
        <Form.Group controlId="formLocation">
          <Form.Label>Location</Form.Label>
          <Form.Control
            type="text"
            value={location}
            onChange={(e) => setLocation(e.target.value)}
          />
        </Form.Group>
        <Form.Group controlId="formStatus">
          <Form.Label>Status</Form.Label>
          <Form.Select
            value={status}
            onChange={(e) => setStatus(e.target.value)}
          >
            {["ACTIVE", "INACTIVE", "SOLD", "EXPIRED"].map((option) => (
              <option key={option} value={option}>
                {option}
              </option>
            ))}
          </Form.Select>
        </Form.Group>
        <Form.Group controlId="formCategoryId">
          <Form.Label>Category</Form.Label>
          <Form.Select
            value={categoryId}
            onChange={(e) => setCategoryId(e.target.value)}
          >
            <option value="">Select Category</option>
            {categories.map((category: any) => (
              <option key={category.id} value={category.id}>
                {category.name}
              </option>
            ))}
          </Form.Select>
        </Form.Group>

        <Form.Group controlId="formImage">
          <Form.Label>Upload Image</Form.Label>
          <Form.Control
            type="file"
            multiple
            accept="image/jpg, image/jpeg, image/png"
            onChange={(e) => handleImageUpload(e)}
          />
        </Form.Group>
        <Button variant="primary" type="submit">
          Update Post
        </Button>
      </Form>
    </Container>
  );
}

export default EditPostPage;
