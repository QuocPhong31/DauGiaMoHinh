import React, { useState } from "react";
import { Container, Row, Col, Form, Button, Spinner } from "react-bootstrap";
import { motion } from "framer-motion";
import confetti from "canvas-confetti";
import { toast, ToastContainer } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

const LienHeGopY = () => {
  const [form, setForm] = useState({
    name: "",
    email: "",
    subject: "",
    message: ""
  });
  const [loading, setLoading] = useState(false);

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const isValid = () =>
    form.name && form.email.includes("@") && form.subject && form.message;

  const triggerConfetti = () => {
    confetti({
      particleCount: 150,
      spread: 80,
      origin: { y: 0.6 }
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!isValid()) {
      toast.error("Vui lòng điền đầy đủ thông tin hợp lệ!");
      return;
    }

    setLoading(true);

    setTimeout(() => {
      setLoading(false);
      toast.success("🎉 Gửi góp ý thành công!");
      triggerConfetti();
      setForm({ name: "", email: "", subject: "", message: "" });
    }, 2000);
  };

  return (
    <Container className="py-5" style={{ background: "#f7f8fa", minHeight: "100vh" }}>
      <ToastContainer />
      <motion.div
        className="p-5 rounded-4 shadow-lg bg-white"
        initial={{ opacity: 0, y: 60 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.7 }}
        style={{ maxWidth: "720px", margin: "0 auto" }}
      >
        <motion.h2
          className="text-center fw-bold mb-4"
          initial={{ opacity: 0, y: -20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.2 }}
        >
          📫 Góp Ý Cho Chúng Tôi
        </motion.h2>
        <p className="text-center text-muted mb-4">
          Chúng tôi luôn lắng nghe. Hãy chia sẻ cảm nhận hoặc phản hồi của bạn.
        </p>

        <Form onSubmit={handleSubmit}>
          <Row className="mb-3">
            <Col md={6}>
              <motion.div
                initial={{ opacity: 0, y: 30 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ delay: 0.3 }}
              >
                <Form.Group>
                  <Form.Label>Họ tên</Form.Label>
                  <Form.Control
                    type="text"
                    name="name"
                    value={form.name}
                    onChange={handleChange}
                    required
                    placeholder="Nguyễn Văn A"
                  />
                </Form.Group>
              </motion.div>
            </Col>
            <Col md={6}>
              <motion.div
                initial={{ opacity: 0, y: 30 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ delay: 0.4 }}
              >
                <Form.Group>
                  <Form.Label>Email</Form.Label>
                  <Form.Control
                    type="email"
                    name="email"
                    value={form.email}
                    onChange={handleChange}
                    required
                    placeholder="you@example.com"
                  />
                </Form.Group>
              </motion.div>
            </Col>
          </Row>

          <motion.div
            className="mb-3"
            initial={{ opacity: 0, y: 30 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: 0.5 }}
          >
            <Form.Group>
              <Form.Label>Tiêu đề</Form.Label>
              <Form.Control
                type="text"
                name="subject"
                value={form.subject}
                onChange={handleChange}
                placeholder="Vấn đề bạn quan tâm..."
                required
              />
            </Form.Group>
          </motion.div>

          <motion.div
            className="mb-4"
            initial={{ opacity: 0, y: 30 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: 0.6 }}
          >
            <Form.Group>
              <Form.Label>Nội dung</Form.Label>
              <Form.Control
                as="textarea"
                name="message"
                rows={5}
                value={form.message}
                onChange={handleChange}
                placeholder="Nội dung góp ý hoặc phản hồi..."
                required
              />
            </Form.Group>
          </motion.div>

          <motion.div
            className="text-center"
            whileHover={{ scale: 1.05 }}
            whileTap={{ scale: 0.95 }}
          >
            <Button
              type="submit"
              variant="dark"
              className="px-5 py-2 rounded-pill fw-semibold"
              disabled={loading}
            >
              {loading ? <Spinner size="sm" animation="border" /> : "Gửi phản hồi"}
            </Button>
          </motion.div>
        </Form>
      </motion.div>
    </Container>
  );
};

export default LienHeGopY;
