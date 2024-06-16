import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Form, Button, Container } from "react-bootstrap";
import toast from "react-hot-toast";
import { useStore } from "../../stores/stores";

export default function CreatePostPage() {
  const navigation = useNavigate();
  const { userStore } = useStore();
  const navigate = useNavigate();

  const [categories, setCategories] = useState<string[]>([]);
  const [images, setImages] = useState<any[]>([]);
  const [data, setData] = useState<CreatePostModel>({
    title: "",
    description: "",
    price: 0,
    location: "",
    userId: userStore.userId,
    phoneNumber: "",
    categoryId: -1,
  });

  useEffect(() => {
    if (!userStore.isLoggedIn) navigation("/not-found");
  }, []);

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/categories"
        );
        response.data.unshift({ id: -1, name: "Select Category" });
        setCategories(response.data);
      } catch (error) {
        toast.error("Couldn't load categories. Please try again later!");
      }
    };

    fetchCategories();
  }, []);

  const handleFileChange = (e: any) => {
    setImages(Array.from(e.target.files));
  };

  const uploadImages = async (postId: any) => {
    for (let image of images) {
      const formData = new FormData();
      formData.append("images", image);
      formData.append("postId", postId);

      try {
        await axios.post("http://localhost:8080/api/images/upload", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        });
      } catch (error) {
        toast.error("An error occurred uploading image!");
      }
    }
  };

  const handleSubmit = async (e: any) => {
    e.preventDefault();

    if (images.length === 0) {
      toast.error("Please add images!");
      return;
    }

    if (data.categoryId === -1) {
      toast.error("Please select valid category!");
      return;
    }

    try {
      const response = await axios.post(
        "http://localhost:8080/api/posts/create",
        data
      );

      await uploadImages(response.data.id);

      toast.success("Post created successfully!");
      navigate(`/posts/${response.data.id}`);
    } catch (error) {
      toast.error("An error occurred uploading post!");
    }
  };

  return (
    <Container className="mt-3 text-center">
      <h1>Create Post Page</h1>
      <Form
        onSubmit={handleSubmit}
        className="d-flex justify-content-center flex-column"
      >
        <Form.Group controlId="formTitle">
          <Form.Label>Title</Form.Label>
          <Form.Control
            type="text"
            value={data.title}
            onChange={(e) => setData({ ...data, title: e.target.value })}
          />
        </Form.Group>

        <Form.Group controlId="formDescription">
          <Form.Label>Description</Form.Label>
          <Form.Control
            type="text"
            value={data.description}
            onChange={(e) => setData({ ...data, description: e.target.value })}
          />
        </Form.Group>

        <Form.Group controlId="formPrice">
          <Form.Label>Price</Form.Label>
          <Form.Control
            type="number"
            value={data.price}
            step="1"
            min="0"
            max="99999"
            onChange={(e) =>
              setData({ ...data, price: Number(e.target.value) })
            }
          />
        </Form.Group>

        <Form.Group controlId="formLocation">
          <Form.Label>Location</Form.Label>
          <Form.Control
            type="text"
            value={data.location}
            onChange={(e) => setData({ ...data, location: e.target.value })}
          />
        </Form.Group>

        <Form.Group controlId="formCategoryId">
          <Form.Label>Category</Form.Label>
          <Form.Select
            value={data.categoryId}
            onChange={(e) =>
              setData({ ...data, categoryId: Number(e.target.value) })
            }
          >
            {categories.map((category: any) => (
              <option key={category.id} value={category.id}>
                {category.name}
              </option>
            ))}
          </Form.Select>
        </Form.Group>

        <Form.Group controlId="formPhoneNumber">
          <Form.Label>Phone Number</Form.Label>
          <Form.Control
            type="text"
            value={data.phoneNumber}
            onChange={(e) => setData({ ...data, phoneNumber: e.target.value })}
          />
        </Form.Group>

        <Form.Group controlId="formImages">
          <Form.Label>Upload Images</Form.Label>
          <Form.Control type="file" multiple onChange={handleFileChange} />
        </Form.Group>

        <Button variant="primary" type="submit">
          Create
        </Button>
      </Form>
    </Container>
  );
}
