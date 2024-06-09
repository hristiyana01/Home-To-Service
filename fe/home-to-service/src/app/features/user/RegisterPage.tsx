import axios from "axios";
import { useEffect, useState } from "react";
import toast from "react-hot-toast";
import { Link, useNavigate } from "react-router-dom";
import { useStore } from "../../stores/stores";

export default function RegisterPage() {
  const navigation = useNavigate();
  const { userStore } = useStore();

  const [input, setInput] = useState<RegisterInput>({
    username: "",
    email: "",
    password: "",
    name: "",
    surname: "",
    phone: "",
    country: "",
    city: "",
  });

  useEffect(() => {
    if (userStore.isLoggedIn) navigation("/not-found");
  }, []);

  const handleRegister = async () => {
    if (
      Object.values(input).some((value) => value.length < 3) ||
      !input.email.includes("@") ||
      input.password.length < 6
    ) {
      toast.error("All the field are required!");
      return;
    }

    try {
      await axios.post("http://localhost:8080/api/users/register", input);
      toast.success("Registered successfully!");
    } catch (error) {
      toast.error("Register failed!");
    }
  };

  return (
    <section className="my-4">
      <div className="mask d-flex align-items-center">
        <div className="container h-100">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-12 col-md-9 col-lg-7 col-xl-6">
              <div className="card">
                <div className="card-body p-5">
                  <h2 className="text-uppercase text-center mb-5">
                    Create an account
                  </h2>

                  <form>
                    <div data-mdb-input-init className="form-outline mb-4">
                      <label className="form-label">Username</label>
                      <input
                        type="text"
                        className="form-control form-control-lg"
                        value={input.username}
                        onChange={(e) =>
                          setInput({ ...input, username: e.target.value })
                        }
                      />
                    </div>

                    <div data-mdb-input-init className="form-outline mb-4">
                      <label className="form-label">Email</label>
                      <input
                        type="email"
                        className="form-control form-control-lg"
                        value={input.email}
                        onChange={(e) =>
                          setInput({ ...input, email: e.target.value })
                        }
                      />
                    </div>

                    <div data-mdb-input-init className="form-outline mb-4">
                      <label className="form-label">Password</label>
                      <input
                        type="password"
                        className="form-control form-control-lg"
                        value={input.password}
                        onChange={(e) =>
                          setInput({ ...input, password: e.target.value })
                        }
                      />
                    </div>

                    <div data-mdb-input-init className="form-outline mb-4">
                      <label className="form-label">Name</label>
                      <input
                        type="text"
                        className="form-control form-control-lg"
                        value={input.name}
                        onChange={(e) =>
                          setInput({ ...input, name: e.target.value })
                        }
                      />
                    </div>

                    <div data-mdb-input-init className="form-outline mb-4">
                      <label className="form-label">Surname</label>
                      <input
                        type="text"
                        className="form-control form-control-lg"
                        value={input.surname}
                        onChange={(e) =>
                          setInput({ ...input, surname: e.target.value })
                        }
                      />
                    </div>

                    <div data-mdb-input-init className="form-outline mb-4">
                      <label className="form-label">Phone</label>
                      <input
                        type="text"
                        className="form-control form-control-lg"
                        value={input.phone}
                        onChange={(e) =>
                          setInput({ ...input, phone: e.target.value })
                        }
                      />
                    </div>

                    <div data-mdb-input-init className="form-outline mb-4">
                      <label className="form-label">Country</label>
                      <input
                        type="text"
                        className="form-control form-control-lg"
                        value={input.country}
                        onChange={(e) =>
                          setInput({ ...input, country: e.target.value })
                        }
                      />
                    </div>

                    <div data-mdb-input-init className="form-outline mb-4">
                      <label className="form-label">City</label>
                      <input
                        type="text"
                        className="form-control form-control-lg"
                        value={input.city}
                        onChange={(e) =>
                          setInput({ ...input, city: e.target.value })
                        }
                      />
                    </div>

                    <div className="d-flex justify-content-center">
                      <button
                        onClick={handleRegister}
                        type="button"
                        className="btn btn-primary btn-block btn-lg"
                      >
                        Register
                      </button>
                    </div>

                    <p className="text-center mt-5 mb-0">
                      Already have an account?{" "}
                      <Link
                        to={`/user/login`}
                        className="fw-bold text-success text-decoration-none"
                      >
                        Login here{" "}
                      </Link>
                    </p>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
