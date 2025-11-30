# 社团管理系统 API 文档

## 1. API 接口总结

| 模块 | 功能描述 | HTTP 方法 | Endpoint | 权限 |
| --- | --- | --- | --- | --- |
| **用户** | 用户注册 | `POST` | `/api/users/register` | 无 |
| | 用户登录 | `POST` | `/api/users/login` | 无 |
| | 获取当前用户信息 | `GET` | `/api/users/me` | 已登录 |
| | 更新当前用户信息 | `PUT` | `/api/users/me` | 已登录 |
| | 修改密码 | `POST` | `/api/users/change-password` | 已登录 |
| | 获取用户列表 | `GET` | `/api/users` | 超级管理员 |
| | 获取指定用户信息 | `GET` | `/api/users/{id}` | 管理员 |
| **社团** | 创建新社团 | `POST` | `/api/clubs` | 超级管理员 |
| | 获取社团列表 | `GET` | `/api/clubs` | 无 |
| | 获取社团详情 | `GET` | `/api/clubs/{id}` | 无 |
| | 更新社团信息 | `PUT` | `/api/clubs/{id}` | 社团管理员 |
| | 删除社团 | `DELETE` | `/api/clubs/{id}` | 超级管理员 |
| | 申请加入社团 | `POST` | `/api/clubs/{id}/join` | 已登录 |
| | 获取入团申请列表 | `GET` | `/api/clubs/{id}/applications` | 社团管理员 |
| | 审核入团申请 | `POST` | `/api/applications/{appId}/review` | 社团管理员 |
| | 获取社团成员列表 | `GET` | `/api/clubs/{id}/members` | 已登录 |
| | 退出社团 | `POST` | `/api/clubs/{id}/leave` | 已登录 |
| | 移出社团成员 | `DELETE` | `/api/clubs/{clubId}/members/{userId}` | 社团管理员 |
| **活动** | 创建新活动 | `POST` | `/api/activities` | 社团管理员 |
| | 获取活动列表 | `GET` | `/api/activities` | 无 |
| | 获取活动详情 | `GET` | `/api/activities/{id}` | 无 |
| | 更新活动信息 | `PUT` | `/api/activities/{id}` | 社团管理员 |
| | 取消活动 | `DELETE` | `/api/activities/{id}` | 社团管理员 |
| | 报名参加活动 | `POST` | `/api/activities/{id}/signup` | 已登录 |
| | 获取活动报名列表 | `GET` | `/api/activities/{id}/participants` | 社团管理员 |
| **新闻公告** | 发布新闻公告 | `POST` | `/api/news` | 管理员 |
| | 获取新闻公告列表 | `GET` | `/api/news` | 无 |
| | 获取新闻公告详情 | `GET` | `/api/news/{id}` | 无 |
| | 更新新闻公告 | `PUT` | `/api/news/{id}` | 管理员 |
| | 删除新闻公告 | `DELETE` | `/api/news/{id}` | 管理员 |
| **财务** | 添加财务记录 | `POST` | `/api/finances/transactions` | 社团管理员 |
| | 获取社团财务记录 | `GET` | `/api/clubs/{id}/transactions` | 社团成员 |
| | 更新财务记录 | `PUT` | `/api/finances/transactions/{id}` | 社团管理员 |
| | 删除财务记录 | `DELETE` | `/api/finances/transactions/{id}` | 社团管理员 |
| **消息** | 发送私信 | `POST` | `/api/messages` | 已登录 |
| | 获取会话列表 | `GET` | `/api/messages/conversations` | 已登录 |
| | 获取与某用户的消息 | `GET` | `/api/messages/conversations/{userId}` | 已登录 |
| | 标记消息为已读 | `POST` | `/api/messages/read` | 已登录 |
| **文件** | 上传文件 | `POST` | `/api/files/upload` | 管理员 |
| | 获取文件列表 | `GET` | `/api/files` | 已登录 |
| | 下载文件 | `GET` | `/api/files/download/{id}` | 已登录 |
| | 删除文件 | `DELETE` | `/api/files/{id}` | 管理员 |
| **AI** | 智能问答 | `POST` | `/api/ai/qwen-chat` | 已登录 |
| | 生成活动文案 | `POST` | `/api/ai/generate-activity-description` | 社团管理员 |
| | 获取智能推荐 | `GET` | `/api/ai/recommendations` | 已登录 |

---

## 2. 基础信息

- **Base URL:** `/api`
- **认证方式:** 大部分需要身份认证的接口，都需要在请求头 (Header) 中携带 `Authorization: Bearer <token>`。`token` 在用户成功登录后从响应中获取。
- **数据格式:** 所有请求和响应的数据格式统一为 `application/json` (文件上传/下载除外)。
- **权限说明:**
    - **无:** 任何人都可以访问。
    - **已登录:** 需要提供有效的 `token`。
    - **社团成员:** 必须是某个社团的成员。
    - **社团管理员:** 必须是指定社团的管理员。
    - **管理员:** 包含社团管理员和超级管理员。
    - **超级管理员:** 拥有最高权限的系统管理员。

---

## 3. 用户模块 (User Module)

### 3.1 用户注册
- **Endpoint:** `POST /api/users/register`
- **描述:** 创建一个新的用户账户。
- **认证:** 无需。

### 3.2 用户登录
- **Endpoint:** `POST /api/users/login`
- **描述:** 用户使用用户名和密码进行登录，成功后返回JWT。
- **认证:** 无需。

### 3.3 获取当前用户信息
- **Endpoint:** `GET /api/users/me`
- **描述:** 获取当前登录用户的详细信息。
- **认证:** 需要。

### 3.4 更新当前用户信息
- **Endpoint:** `PUT /api/users/me`
- **描述:** 更新当前登录用户的个人资料（如邮箱、简介等）。
- **请求体:** 包含需要更新的字段。
- **认证:** 需要。

### 3.5 修改密码
- **Endpoint:** `POST /api/users/change-password`
- **描述:** 修改当前登录用户的密码。
- **请求体:** `{ "currentPassword": "...", "newPassword": "..." }`
- **认证:** 需要。

---

## 4. 社团模块 (Club Module)

### 4.1 创建新社团
- **Endpoint:** `POST /api/clubs`
- **描述:** 超级管理员创建一个新的社团。
- **认证:** 超级管理员。

### 4.2 获取社团列表
- **Endpoint:** `GET /api/clubs`
- **描述:** 获取所有社团的列表，支持分页和搜索。
- **认证:** 无需。

### 4.3 获取社团详情
- **Endpoint:** `GET /api/clubs/{id}`
- **描述:** 获取指定ID社团的详细信息，包括成员列表、活动等。
- **认证:** 无需。

### 4.4 更新社团信息
- **Endpoint:** `PUT /api/clubs/{id}`
- **描述:** 社团管理员更新社团的基本信息（如简介、公告等）。
- **认证:** 社团管理员。

### 4.5 申请加入社团
- **Endpoint:** `POST /api/clubs/{id}/join`
- **描述:** 用户申请加入指定的社团。
- **请求体:** `{ "reason": "我非常喜欢..." }`
- **认证:** 需要。

### 4.6 审核入团申请
- **Endpoint:** `POST /api/applications/{appId}/review`
- **描述:** 社团管理员审核用户的入团申请。
- **URL 参数:** `appId` - 申请记录的ID。
- **请求体:** `{ "approved": true, "message": "欢迎加入！" }`
- **认证:** 社团管理员。

### 4.7 退出社团
- **Endpoint:** `POST /api/clubs/{id}/leave`
- **描述:** 成员主动退出社团。
- **认证:** 需要。

---

## 5. 活动模块 (Activity Module)

### 5.1 创建新活动
- **Endpoint:** `POST /api/activities`
- **描述:** 社团管理员发布新的活动。
- **认证:** 社团管理员。

### 5.2 获取活动列表
- **Endpoint:** `GET /api/activities`
- **描述:** 获取所有公开活动，支持按社团、时间等过滤。
- **认证:** 无需。

### 5.3 获取活动详情
- **Endpoint:** `GET /api/activities/{id}`
- **描述:** 获取指定ID活动的详细信息。
- **认证:** 无需。

### 5.4 报名参加活动
- **Endpoint:** `POST /api/activities/{id}/signup`
- **描述:** 成员报名参加某个活动。
- **认证:** 需要。

---

## 6. 新闻公告模块 (News Module)

### 6.1 发布新闻公告
- **Endpoint:** `POST /api/news`
- **描述:** 管理员发布新闻或公告。
- **认证:** 管理员。

### 6.2 获取新闻公告列表
- **Endpoint:** `GET /api/news`
- **描述:** 获取所有新闻和公告，支持分页。
- **认证:** 无需。

---

## 7. 财务模块 (Finance Module)

### 7.1 添加财务记录
- **Endpoint:** `POST /api/finances/transactions`
- **描述:** 社团管理员添加一笔社团的收入或支出记录。
- **请求体:** `{ "clubId": 1, "type": "EXPENSE", "amount": 50.0, "description": "购买活动用品" }`
- **认证:** 社团管理员。

### 7.2 获取社团财务记录
- **Endpoint:** `GET /api/clubs/{id}/transactions`
- **描述:** 获取指定社团的财务明细。
- **认证:** 社团成员。

---

## 8. 消息与文件模块 (Messaging & File Module)

### 8.1 发送私信
- **Endpoint:** `POST /api/messages`
- **描述:** 向指定用户（如社长）发送私信。
- **认证:** 需要。

### 8.2 获取会话列表
- **Endpoint:** `GET /api/messages/conversations`
- **描述:** 获取当前用户的消息会话列表。
- **认证:** 需要。

### 8.3 上传文件
- **Endpoint:** `POST /api/files/upload`
- **描述:** 管理员上传文件，请求格式为 `multipart/form-data`。
- **认证:** 管理员。

### 8.4 获取文件列表
- **Endpoint:** `GET /api/files`
- **描述:** 获取可访问的文件列表，支持按社团过滤。
- **认证:** 需要。

### 8.5 下载文件
- **Endpoint:** `GET /api/files/download/{id}`
- **描述:** 下载指定ID的文件。
- **认证:** 需要。

---

## 9. AI 模块 (AI Module)

### 9.1 智能问答
- **Endpoint:** `POST /api/ai/qwen-chat`
- **描述:** 向基于QWen的AI助手提问。
- **认证:** 需要。

### 9.2 智能生成活动文案
- **Endpoint:** `POST /api/ai/generate-activity-description`
- **描述:** 根据要点智能生成活动描述。
- **认证:** 社团管理员。

### 9.3 获取智能推荐
- **Endpoint:** `GET /api/ai/recommendations`
- **描述:** 为当前用户获取个性化的社团或活动推荐。
- **认证:** 需要。
