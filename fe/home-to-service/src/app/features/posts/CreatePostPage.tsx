import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Form, Button, Container, Row, Col } from "react-bootstrap";
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

  // Error states
  const [errors, setErrors] = useState({
    title: "",
    description: "",
    location: "",
    images: "",
    categoryId: "",
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

    // Reset errors
    setErrors({
      title: "",
      description: "",
      location: "",
      images: "",
      categoryId: "",
    });

    // Validate inputs
    let formIsValid = true;
    let newErrors = {
      title: "",
      description: "",
      location: "",
      images: "",
      categoryId: "",
    };

    if (!data.title) {
      newErrors.title = "Title is required";
      formIsValid = false;
    }
    if (!data.description) {
      newErrors.description = "Description is required";
      formIsValid = false;
    }
    if (!data.location) {
      newErrors.location = "Location is required";
      formIsValid = false;
    }
    if (data.categoryId === -1) {
      newErrors.categoryId = "Please select a valid category";
      formIsValid = false;
    }
    if (images.length === 0) {
      newErrors.images = "At least one image is required";
      formIsValid = false;
    }

    if (!formIsValid) {
      setErrors(newErrors);
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
    <Container className="mt-3">
      <Row>
        <Col md={6} className="mx-auto">
          <h1 className="text-center">Create Post Page</h1>
          <Form
            onSubmit={handleSubmit}
            className="d-flex justify-content-center flex-column"
          >
            <Form.Group controlId="formTitle" className="mt-3">
              <Form.Label>Title</Form.Label>
              <Form.Control
                type="text"
                value={data.title}
                onChange={(e) => setData({ ...data, title: e.target.value })}
                isInvalid={!!errors.title}
              />
              <Form.Control.Feedback type="invalid">
                {errors.title}
              </Form.Control.Feedback>
            </Form.Group>

            <Form.Group controlId="formDescription" className="mt-3">
              <Form.Label>Description</Form.Label>
              <Form.Control
                className="mx-auto"
                type="text"
                value={data.description}
                onChange={(e) =>
                  setData({ ...data, description: e.target.value })
                }
                isInvalid={!!errors.description}
              />
              <Form.Control.Feedback type="invalid">
                {errors.description}
              </Form.Control.Feedback>
            </Form.Group>

            <Form.Group controlId="formPrice" className="mt-3">
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

            <Form.Group controlId="formLocation" className="mt-3">
              <Form.Label>Location</Form.Label>
              <Form.Control
                type="text"
                value={data.location}
                onChange={(e) => setData({ ...data, location: e.target.value })}
                isInvalid={!!errors.location}
              />
              <Form.Control.Feedback type="invalid">
                {errors.location}
              </Form.Control.Feedback>
            </Form.Group>

            <Form.Group controlId="formCategoryId" className="mt-3">
              <Form.Label>Category</Form.Label>
              <Form.Select
                value={data.categoryId}
                onChange={(e) =>
                  setData({ ...data, categoryId: Number(e.target.value) })
                }
                isInvalid={!!errors.categoryId}
              >
                {categories.map((category: any) => (
                  <option key={category.id} value={category.id}>
                    {category.name}
                  </option>
                ))}
              </Form.Select>
              <Form.Control.Feedback type="invalid">
                {errors.categoryId}
              </Form.Control.Feedback>
            </Form.Group>

            <Form.Group controlId="formPhoneNumber" className="mt-3">
              <Form.Label>Phone Number</Form.Label>
              <Form.Control
                type="text"
                value={data.phoneNumber}
                onChange={(e) =>
                  setData({ ...data, phoneNumber: e.target.value })
                }
              />
            </Form.Group>

            <Form.Group controlId="formImages" className="mt-3">
              <Form.Label>Upload Images</Form.Label>
              <Form.Control
                type="file"
                multiple
                onChange={handleFileChange}
                isInvalid={!!errors.images}
              />
              <Form.Control.Feedback type="invalid">
                {errors.images}
              </Form.Control.Feedback>
            </Form.Group>

            <Button variant="success" type="submit" className="mt-4">
              Create
            </Button>
          </Form>
        </Col>
      </Row>
    </Container>
  );
}
